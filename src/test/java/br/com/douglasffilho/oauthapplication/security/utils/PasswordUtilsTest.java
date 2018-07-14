package br.com.douglasffilho.oauthapplication.security.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PasswordUtilsTest {

    @Test
    public void shouldEncryptPassword() {
        final boolean verify = PasswordUtils.validatePassword("admin", "$2a$10$cItDLc5iApFxDgWc2qiPz.4zyUBvSRp79nB3SXGyw2g176rtz3p4G");
        Assert.assertTrue(verify);
    }

}
