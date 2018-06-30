package com.example.jeffliang.coen268finalproject.util;

import com.example.jeffliang.coen268finalproject.SignUpI;
import com.example.jeffliang.coen268finalproject.SignUpII;
import com.example.jeffliang.coen268finalproject.SignUpIII;

public class RegistrationControl {

    public static SignUpI signup1 = null;
    public static SignUpII signup2 = null;
    public static SignUpIII signup3 = null;


    public static void resetRegistration() {
        signup1 = null;
        signup2 = null;
        signup3 = null;
    }
}
