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
package io.gravitee.am.model.log;

import java.util.Date;

/**
 * @author Titouan COMPIEGNE (titouan.compiegne at graviteesource.com)
 * @author GraviteeSource Team
 */
public class Audit {

    /**
     * The log technical id
     */
    private String id;

    /**
     * The log transaction id for event correlation
     */
    private String transactionId;

    /**
     * The log event type
     */
    private String type;

    /**
     * Security domain who triggered the action
     */
    private String domain;

    /**
     * The client that performs the event (OAuth client or HTTP client (e.g web browser) with ip address, user agent, geographical information)
     */
    private AuditClient client;

    /**
     * Describes the user, app, client, or other entity who performed an action on a target
     */
    private AuditEntity actor;

    /**
     * The entity upon which an actor performs an action
     */
    private AuditEntity target;

    /**
     * The result of an action and the reason for that result
     */
    private AuditResult result;

    /**
     * The date when the event was created
     */
    private Date createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public AuditClient getClient() {
        return client;
    }

    public void setClient(AuditClient client) {
        this.client = client;
    }

    public AuditEntity getActor() {
        return actor;
    }

    public void setActor(AuditEntity actor) {
        this.actor = actor;
    }

    public AuditEntity getTarget() {
        return target;
    }

    public void setTarget(AuditEntity target) {
        this.target = target;
    }

    public AuditResult getResult() {
        return result;
    }

    public void setResult(AuditResult result) {
        this.result = result;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
