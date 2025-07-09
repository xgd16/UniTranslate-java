package net.todream.uni_translate.uni_translate.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Common {
    public static String md5(String input) {
        try {
            // 创建 MessageDigest 实例，指定MD5算法
            MessageDigest md = MessageDigest.getInstance("MD5");
            
            // 添加要计算摘要的字节
            md.update(input.getBytes());
            
            // 完成哈希计算，获得摘要
            byte[] digest = md.digest();
            
            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : digest) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
            
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
}
