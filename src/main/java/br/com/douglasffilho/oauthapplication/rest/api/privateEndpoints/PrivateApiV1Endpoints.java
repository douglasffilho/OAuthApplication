package br.com.douglasffilho.oauthapplication.rest.api.privateEndpoints;

public interface PrivateApiV1Endpoints {

    static final String PRIVATE_API_V1 = "/private/api/v1";

    static final String PRIVATE_API_V1_USERS_ROOT_ENDPOINT = PRIVATE_API_V1 + "/users";

    static final String PRIVATE_API_V1_USERS_FIND_BY_NAME_ENDPOINT = "/{name}";

    static final String PRIVATE_API_V1_USERS_SAVE_ENDPOINT = "/save";

}
