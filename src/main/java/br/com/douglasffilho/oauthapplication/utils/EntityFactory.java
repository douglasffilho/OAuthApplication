package br.com.douglasffilho.oauthapplication.utils;

public interface EntityFactory<T> {
    public T createValid();

    public T createTest();
}
