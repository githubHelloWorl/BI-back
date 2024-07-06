package com.yupi.springbootinit.test;

import org.springframework.util.DigestUtils;
import java.io.IOException;

public class test1 {

    public static final String SALT = "BI-chen";

    public static void main(String[] args) throws IOException {
        test1();
    }

    public static void test1() throws IOException {

        String userPassword = "123BI789";

        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        System.out.println(encryptPassword);

    }

}
