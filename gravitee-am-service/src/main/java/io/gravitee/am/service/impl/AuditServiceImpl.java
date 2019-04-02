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
package io.gravitee.am.service.impl;

import io.gravitee.am.common.utils.RandomString;
import io.gravitee.am.model.log.Audit;
import io.gravitee.am.reporter.api.Reportable;
import io.gravitee.am.service.AuditService;
import io.gravitee.am.service.reporter.AuditReporterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.Executors;

/**
 * @author Titouan COMPIEGNE (titouan.compiegne at graviteesource.com)
 * @author GraviteeSource Team
 */
@Component
public class AuditServiceImpl implements AuditService {

    @Autowired
    private AuditReporterService auditReporterService;

    @Override
    public void report(Audit audit) {
        Executors.newCachedThreadPool().execute(() -> {
            auditReporterService.report(convert(audit));
        });
    }

    private Reportable convert(Audit audit) {
        io.gravitee.am.reporter.api.audit.Audit reportable = new io.gravitee.am.reporter.api.audit.Audit();
        reportable.setId(RandomString.generate());
        reportable.setDomain(audit.getDomain());
        reportable.setTimestamp(Instant.now());

        return reportable;
    }
}
