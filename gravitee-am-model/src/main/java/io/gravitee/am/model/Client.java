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
package io.gravitee.am.model;

import io.gravitee.am.common.oauth2.GrantType;
import io.gravitee.am.common.oauth2.ResponseType;
import io.gravitee.am.common.oidc.ApplicationType;
import io.gravitee.am.common.oidc.ClientAuthenticationMethod;
import io.gravitee.am.model.oidc.JWKSet;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * @author David BRASSELY (david.brassely at graviteesource.com)
 * @author GraviteeSource Team
 *
 * See https://openid.net/specs/openid-connect-registration-1_0.html#ClientMetadata
 */

public class Client {

    public final static int DEFAULT_ACCESS_TOKEN_VALIDITY_SECONDS = 7200;
    public final static int DEFAULT_REFRESH_TOKEN_VALIDITY_SECONDS = 14400;
    public final static int DEFAULT_ID_TOKEN_VALIDITY_SECONDS = 14400;
    public final static List<String> DEFAULT_GRANT_TYPES =  Arrays.asList(GrantType.AUTHORIZATION_CODE);
    public final static List<String> DEFAULT_RESPONSE_TYPES = Arrays.asList(ResponseType.CODE);

    private String id;

    private String clientId;

    private String clientSecret;

    private List<String> redirectUris;

    private List<String> authorizedGrantTypes = DEFAULT_GRANT_TYPES;

    private List<String> responseTypes = DEFAULT_RESPONSE_TYPES;

    //Default value must be web.
    private String applicationType = ApplicationType.WEB;

    private List<String> contacts;

    private String clientName;

    private String logoUri;

    private String ClientUri;

    private String policyUri;

    private String tosUri;

    private String jwksUri;

    private JWKSet jwks;

    private String sectorIdentifierUri;

    private String subjectType;

    private String idTokenSignedResponseAlg;

    private String idTokenEncryptedResponseAlg;

    private String idTokenEncryptedResponseEnc;

    private String userinfoSignedResponseAlg;

    private String userinfoEncryptedResponseAlg;

    private String userinfoEncryptedResponseEnc;

    private String requestObjectSigningAlg;

    private String requestObjectEncryptionAlg;

    private String requestObjectEncryptionEnc;

    private String tokenEndpointAuthMethod = ClientAuthenticationMethod.CLIENT_SECRET_BASIC;

    private String tokenEndpointAuthSigningAlg;

    private Integer defaultMaxAge;

    private Boolean requireAuthTime = false;

    private List<String> defaultACRvalues;

    private String initiateLoginUri;

    private List<String> requestUris;

    private List<String> scopes;

    private String softwareId; //Should be UUID

    private String softwareVersion;

    private String softwareStatement; //Should be JWT

    private String registrationAccessToken;

    private String registrationClientUri;

    private Date clientIdIssuedAt;

    private Date clientSecretExpiresAt;

    private List<String> autoApproveScopes;

    private int accessTokenValiditySeconds = DEFAULT_ACCESS_TOKEN_VALIDITY_SECONDS;

    private int refreshTokenValiditySeconds = DEFAULT_REFRESH_TOKEN_VALIDITY_SECONDS;

    private int idTokenValiditySeconds = DEFAULT_ID_TOKEN_VALIDITY_SECONDS;

    private Map<String, Object> idTokenCustomClaims;

    /**
     * Security domain associated to the client
     */
    private String domain;

    /**
     * Client enabled.
     */
    private boolean enabled;

    /**
     * The Client creation date
     */
    private Date createdAt;

    /**
     * The Client last updated date
     */
    private Date updatedAt;

    private Set<String> identities;

    private Set<String> oauth2Identities;

    private String certificate;

    private boolean enhanceScopesWithUserPermissions;

    private Map<String, Integer> scopeApprovals;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public List<String> getRedirectUris() {
        return redirectUris;
    }

    public void setRedirectUris(List<String> redirectUris) {
        this.redirectUris = redirectUris;
    }

    public List<String> getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(List<String> grantTypes) { this.authorizedGrantTypes = grantTypes; }

    public List<String> getResponseTypes() {
        return responseTypes;
    }

    public void setResponseTypes(List<String> responseTypes) {
        this.responseTypes = responseTypes;
    }

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    public List<String> getContacts() {
        return contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getLogoUri() {
        return logoUri;
    }

    public void setLogoUri(String logoUri) {
        this.logoUri = logoUri;
    }

    public String getClientUri() {
        return ClientUri;
    }

    public void setClientUri(String clientUri) {
        ClientUri = clientUri;
    }

    public String getPolicyUri() {
        return policyUri;
    }

    public void setPolicyUri(String policyUri) {
        this.policyUri = policyUri;
    }

    public String getTosUri() {
        return tosUri;
    }

    public void setTosUri(String tosUri) {
        this.tosUri = tosUri;
    }

    public String getJwksUri() {
        return jwksUri;
    }

    public void setJwksUri(String jwksUri) {
        this.jwksUri = jwksUri;
    }

    public JWKSet getJwks() {
        return jwks;
    }

    public void setJwks(JWKSet jwks) {
        this.jwks = jwks;
    }

    public String getSectorIdentifierUri() {
        return sectorIdentifierUri;
    }

    public void setSectorIdentifierUri(String sectorIdentifierUri) {
        this.sectorIdentifierUri = sectorIdentifierUri;
    }

    public String getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(String subjectType) {
        this.subjectType = subjectType;
    }

    public String getIdTokenSignedResponseAlg() {
        return idTokenSignedResponseAlg;
    }

    public void setIdTokenSignedResponseAlg(String idTokenSignedResponseAlg) {
        this.idTokenSignedResponseAlg = idTokenSignedResponseAlg;
    }

    public String getIdTokenEncryptedResponseAlg() {
        return idTokenEncryptedResponseAlg;
    }

    public void setIdTokenEncryptedResponseAlg(String idTokenEncryptedResponseAlg) {
        this.idTokenEncryptedResponseAlg = idTokenEncryptedResponseAlg;
    }

    public String getIdTokenEncryptedResponseEnc() {
        return idTokenEncryptedResponseEnc;
    }

    public void setIdTokenEncryptedResponseEnc(String idTokenEncryptedResponseEnc) {
        this.idTokenEncryptedResponseEnc = idTokenEncryptedResponseEnc;
    }

    public String getUserinfoSignedResponseAlg() {
        return userinfoSignedResponseAlg;
    }

    public void setUserinfoSignedResponseAlg(String userinfoSignedResponseAlg) {
        this.userinfoSignedResponseAlg = userinfoSignedResponseAlg;
    }

    public String getUserinfoEncryptedResponseAlg() {
        return userinfoEncryptedResponseAlg;
    }

    public void setUserinfoEncryptedResponseAlg(String userinfoEncryptedResponseAlg) {
        this.userinfoEncryptedResponseAlg = userinfoEncryptedResponseAlg;
    }

    public String getUserinfoEncryptedResponseEnc() {
        return userinfoEncryptedResponseEnc;
    }

    public void setUserinfoEncryptedResponseEnc(String userinfoEncryptedResponseEnc) {
        this.userinfoEncryptedResponseEnc = userinfoEncryptedResponseEnc;
    }

    public String getRequestObjectSigningAlg() {
        return requestObjectSigningAlg;
    }

    public void setRequestObjectSigningAlg(String requestObjectSigningAlg) {
        this.requestObjectSigningAlg = requestObjectSigningAlg;
    }

    public String getRequestObjectEncryptionAlg() {
        return requestObjectEncryptionAlg;
    }

    public void setRequestObjectEncryptionAlg(String requestObjectEncryptionAlg) {
        this.requestObjectEncryptionAlg = requestObjectEncryptionAlg;
    }

    public String getRequestObjectEncryptionEnc() {
        return requestObjectEncryptionEnc;
    }

    public void setRequestObjectEncryptionEnc(String requestObjectEncryptionEnc) {
        this.requestObjectEncryptionEnc = requestObjectEncryptionEnc;
    }

    public String getTokenEndpointAuthMethod() {
        return tokenEndpointAuthMethod;
    }

    public void setTokenEndpointAuthMethod(String tokenEndpointAuthMethod) {
        this.tokenEndpointAuthMethod = tokenEndpointAuthMethod;
    }

    public String getTokenEndpointAuthSigningAlg() {
        return tokenEndpointAuthSigningAlg;
    }

    public void setTokenEndpointAuthSigningAlg(String tokenEndpointAuthSigningAlg) {
        this.tokenEndpointAuthSigningAlg = tokenEndpointAuthSigningAlg;
    }

    public Integer getDefaultMaxAge() {
        return defaultMaxAge;
    }

    public void setDefaultMaxAge(Integer defaultMaxAge) {
        this.defaultMaxAge = defaultMaxAge;
    }

    public Boolean getRequireAuthTime() {
        return requireAuthTime;
    }

    public void setRequireAuthTime(Boolean requireAuthTime) {
        this.requireAuthTime = requireAuthTime;
    }

    public List<String> getDefaultACRvalues() {
        return defaultACRvalues;
    }

    public void setDefaultACRvalues(List<String> defaultACRvalues) {
        this.defaultACRvalues = defaultACRvalues;
    }

    public String getInitiateLoginUri() {
        return initiateLoginUri;
    }

    public void setInitiateLoginUri(String initiateLoginUri) {
        this.initiateLoginUri = initiateLoginUri;
    }

    public List<String> getRequestUris() {
        return requestUris;
    }

    public void setRequestUris(List<String> requestUris) {
        this.requestUris = requestUris;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    public String getSoftwareId() {
        return softwareId;
    }

    public void setSoftwareId(String softwareId) {
        this.softwareId = softwareId;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public String getSoftwareStatement() {
        return softwareStatement;
    }

    public void setSoftwareStatement(String softwareStatement) {
        this.softwareStatement = softwareStatement;
    }

    public String getRegistrationAccessToken() {
        return registrationAccessToken;
    }

    public void setRegistrationAccessToken(String registrationAccessToken) {
        this.registrationAccessToken = registrationAccessToken;
    }

    public String getRegistrationClientUri() {
        return registrationClientUri;
    }

    public void setRegistrationClientUri(String registrationClientUri) {
        this.registrationClientUri = registrationClientUri;
    }

    public Date getClientIdIssuedAt() {
        return clientIdIssuedAt;
    }

    public void setClientIdIssuedAt(Date clientIdIssuedAt) {
        this.clientIdIssuedAt = clientIdIssuedAt;
    }

    public Date getClientSecretExpiresAt() {
        return clientSecretExpiresAt;
    }

    public void setClientSecretExpiresAt(Date clientSecretExpiresAt) {
        //Enforce date to UTC time zone.
        if (clientSecretExpiresAt != null) {
            this.clientSecretExpiresAt = Date.from(
                    ZonedDateTime.ofInstant(clientSecretExpiresAt.toInstant(), ZoneId.of("UTC")).toInstant()
            );
        }
    }

    public List<String> getAutoApproveScopes() {
        return autoApproveScopes;
    }

    public void setAutoApproveScopes(List<String> autoApproveScopes) {
        this.autoApproveScopes = autoApproveScopes;
    }

    public int getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public int getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<String> getIdentities() {
        return identities;
    }

    public void setIdentities(Set<String> identities) {
        this.identities = identities;
    }

    public Set<String> getOauth2Identities() {
        return oauth2Identities;
    }

    public void setOauth2Identities(Set<String> oauth2Identities) {
        this.oauth2Identities = oauth2Identities;
    }

    public int getIdTokenValiditySeconds() {
        return idTokenValiditySeconds;
    }

    public void setIdTokenValiditySeconds(int idTokenValiditySeconds) {
        this.idTokenValiditySeconds = idTokenValiditySeconds;
    }

    public Map<String, Object> getIdTokenCustomClaims() {
        return idTokenCustomClaims;
    }

    public void setIdTokenCustomClaims(Map<String, Object> idTokenCustomClaims) {
        this.idTokenCustomClaims = idTokenCustomClaims;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public boolean isEnhanceScopesWithUserPermissions() {
        return enhanceScopesWithUserPermissions;
    }

    public void setEnhanceScopesWithUserPermissions(boolean enhanceScopesWithUserPermissions) {
        this.enhanceScopesWithUserPermissions = enhanceScopesWithUserPermissions;
    }

    public Map<String, Integer> getScopeApprovals() {
        return scopeApprovals;
    }

    public void setScopeApprovals(Map<String, Integer> scopeApprovals) {
        this.scopeApprovals = scopeApprovals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
