/**
 * Copyright (C) 2015 The Gravitee team (http://gravitee.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.gravitee.am.reporter.mongodb.audit;

import com.mongodb.client.model.InsertOneModel;
import com.mongodb.client.model.WriteModel;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.gravitee.am.model.common.Page;
import io.gravitee.am.reporter.api.audit.Audit;
import io.gravitee.am.reporter.api.audit.AuditReportableCriteria;
import io.gravitee.am.reporter.api.audit.AuditReporter;
import io.gravitee.am.reporter.mongodb.MongoReporterConfiguration;
import io.gravitee.common.service.AbstractService;
import io.gravitee.reporter.api.Reportable;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.processors.PublishProcessor;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import java.sql.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Titouan COMPIEGNE (titouan.compiegne at graviteesource.com)
 * @author GraviteeSource Team
 */
@Import({io.gravitee.am.reporter.mongodb.spring.MongoReporterConfiguration.class})
public class MongoAuditReporter extends AbstractService implements AuditReporter {

    private static final Logger logger = LoggerFactory.getLogger(MongoAuditReporter.class);
    private static final String FIELD_ID = "_id";
    private static final String FIELD_TRANSACTIONAL_ID = "transactionalId";
    private static final String FIELD_CREATED_AT = "createdAt";

    @Autowired
    private MongoClient mongoClient;

    @Autowired
    private MongoReporterConfiguration configuration;

    private MongoCollection<Document> reportableCollection;

    private final PublishProcessor<Audit> bulkProcessor = PublishProcessor.create();

    @Override
    public Single<Page<Audit>> search(AuditReportableCriteria criteria, int page, int size) {
        return null;
    }

    @Override
    public void report(Reportable reportable) {
        bulkProcessor
                .onNext((Audit) reportable);
    }
    @Override
    protected void doStart() throws Exception {
        super.doStart();

        // init reportable collection
        reportableCollection = this.mongoClient.getDatabase(this.configuration.getDatabase()).getCollection(this.configuration.getReportableCollection());

        // init bulk processor
        bulkProcessor.buffer(
                configuration.getFlushInterval(),
                TimeUnit.SECONDS,
                configuration.getBulkActions())
                .flatMap(this::bulk)
                .doOnError(throwable -> logger.error("An error occurs while indexing data into MongoDB", throwable))
                .subscribe();
    }

    @Override
    protected void doStop() throws Exception {
        super.doStop();
        try {
            mongoClient.close();
        } catch(Exception ex) {
            logger.error("Failed to close mongoDB client", ex);
        }
    }

    private Flowable bulk(List<Audit> audits) {
        if (audits == null || audits.isEmpty()) {
            return Flowable.empty();
        }

        return Flowable.fromPublisher(reportableCollection.bulkWrite(this.convert(audits)));
    }

    private List<WriteModel<Document>> convert(List<Audit> audits) {
        return audits.stream().map(audit -> new InsertOneModel<>(convert(audit))).collect(Collectors.toList());
    }

    private Document convert(Audit audit) {
        Document document = new Document();
        document.put(FIELD_ID, audit.getId());
        document.put(FIELD_CREATED_AT, Date.from(audit.timestamp()));
        return document;
    }
}
