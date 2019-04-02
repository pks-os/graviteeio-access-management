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
package io.gravitee.am.management.service.impl;

import io.gravitee.am.management.service.AuditReporterManager;
import io.gravitee.am.plugins.reporter.core.ReporterPluginManager;
import io.gravitee.am.reporter.api.provider.Reporter;
import io.gravitee.am.service.ReporterService;
import io.gravitee.am.service.reporter.impl.AuditReporterVerticle;
import io.gravitee.am.service.reporter.vertx.EventBusReporterWrapper;
import io.gravitee.common.service.AbstractService;
import io.reactivex.Single;
import io.vertx.reactivex.core.RxHelper;
import io.vertx.reactivex.core.Vertx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Titouan COMPIEGNE (titouan.compiegne at graviteesource.com)
 * @author GraviteeSource Team
 */
@Component
public class AuditReporterManagerImpl extends AbstractService<AuditReporterManager>  implements AuditReporterManager {

    public static final Logger logger = LoggerFactory.getLogger(AuditReporterManagerImpl.class);
    public static final String ADMIN_DOMAIN = "admin";
    private String deploymentId;

    @Autowired
    private ReporterPluginManager reporterPluginManager;

    @Autowired
    private ReporterService reporterService;

    @Autowired
    private Vertx vertx;

    @Autowired
    private ApplicationContext applicationContext;

    private Map<String, Reporter> auditReporters = new HashMap<>();

    @Override
    protected void doStart() throws Exception {
        super.doStart();

        reporterService.findByDomain(ADMIN_DOMAIN)
                .subscribe(reporters -> {
                    reporters.forEach(reporter -> {
                        Reporter auditReporter = reporterPluginManager.create(reporter.getType(), reporter.getConfiguration());
                        if (auditReporter != null) {
                            logger.info("Initializing audit reporter provider : {}", reporter.getId());
                            auditReporters.put(reporter.getId(), new EventBusReporterWrapper(vertx, reporter.getDomain(), auditReporter));
                        }
                    });
                    deployReporterVerticle(auditReporters.values());
                });
    }

    private void deployReporterVerticle(Collection<Reporter> reporters) {
        Single<String> deployment = RxHelper.deployVerticle(vertx, applicationContext.getBean(AuditReporterVerticle.class));

        deployment.subscribe(id -> {
            // Deployed
            deploymentId = id;
            if (! reporters.isEmpty()) {
                for (io.gravitee.reporter.api.Reporter reporter : reporters) {
                    try {
                        logger.info("Starting reporter: {}", reporter);
                        reporter.start();
                    } catch (Exception ex) {
                        logger.error("Unexpected error while starting reporter", ex);
                    }
                }
            } else {
                logger.info("\tThere is no reporter to start");
            }
        }, err -> {
            // Could not deploy
            logger.error("Reporter service can not be started", err);
        });
    }
}
