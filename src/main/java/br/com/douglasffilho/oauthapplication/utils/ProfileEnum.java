package br.com.douglasffilho.oauthapplication.utils;

public enum ProfileEnum {
    ROLE_ADMIN("ADMIN"),
    ROLE_USER("USER");

    private final String value;

    ProfileEnum(final String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
