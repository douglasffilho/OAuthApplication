package br.com.douglasffilho.oauthapplication.utils;

import java.util.Arrays;

public enum ProfileEnum {

    ROLE_ADMIN,
    ROLE_USER,
    ROLE_TEST;

    public static ProfileEnum getByName(final String name) throws IllegalArgumentException {
        return Arrays
                .stream(ProfileEnum.values())
                .filter(profileEnum -> profileEnum.name().equals(name))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
