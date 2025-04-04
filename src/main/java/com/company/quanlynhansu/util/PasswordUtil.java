package com.company.quanlynhansu.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    public static void main(String[] args) {
        String plainPassword = "admin";
        String hashed = BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
        System.out.println(hashed);
    }
}
