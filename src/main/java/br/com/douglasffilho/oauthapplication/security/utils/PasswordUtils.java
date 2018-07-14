package br.com.douglasffilho.oauthapplication.security.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {

    public static String generateBCrypt(final String password) {
        if (password != null && password.length() > 0) {
            final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            return passwordEncoder.encode(password);
        }
        return password;
    }

    public static boolean validatePassword(final String password, final String encodedPassword) {
        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(password, encodedPassword);
    }

}
