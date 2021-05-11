package be.ulb.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcryptPasswordEncoderSingleton
{
    private static BCryptPasswordEncoder INSTANCE = null;

    private BcryptPasswordEncoderSingleton()
    {
        INSTANCE = new BCryptPasswordEncoder();
    }

    public static BCryptPasswordEncoder getEncoder()
    {
        if (INSTANCE == null) {
            new BcryptPasswordEncoderSingleton();
        }
        return INSTANCE;
    }
}