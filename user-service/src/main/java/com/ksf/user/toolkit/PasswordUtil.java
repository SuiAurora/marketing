package com.ksf.user.toolkit;

import cn.hutool.crypto.digest.BCrypt;

/**
 * description: 密码工具类
 * author: Aurora
 */
public class PasswordUtil {

    /**
     * 加密密码
     *
     * @param plainPassword 未加密的密码
     * @return 加密后的密码
     */
    public static String encryptPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    /**
     * 校验密码
     *
     * @param plainPassword     未加密的密码
     * @param encryptedPassword 已加密的密码
     * @return true：校验成功；false：校验失败
     */
    public static boolean checkPassword(String plainPassword, String encryptedPassword) {
        return BCrypt.checkpw(plainPassword, encryptedPassword);
    }

//    public static void main(String[] args) {
//        String plainPassword = "123456";
//        String encryptedPassword = encryptPassword(plainPassword);
//        System.out.println("Encrypted Password: " + encryptedPassword);
//    }

//    public static void main(String[] args) {
//        String plainPassword = "123456";
//        String encryptedPassword = "$2a$10$C.FNj3cT0AgdJ8za5hZuS.tWdrjJwvjAaVq7P/uMJ5nQ3W3Jbjoze";
//        boolean isValid = checkPassword(plainPassword, encryptedPassword);
//        System.out.println("Is Valid: " + isValid);
//    }
}
