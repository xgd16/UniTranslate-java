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

    /**
     * 从语言对字符串中提取源语言
     * @param langPair 语言对字符串，格式如 "zh-CHS2ja"、"en2fr" 等
     * @return 源语言代码
     */
    public static String extractSourceLanguage(String langPair) {
        // 查找第一个数字"2"的位置（语言对分隔符）
        int splitIndex = langPair.indexOf('2');
        
        // 确保找到分隔符且不在字符串开头
        if (splitIndex > 0) {
            return langPair.substring(0, splitIndex);
        }
        
        // 处理无效格式的情况
        return langPair; // 或抛出异常 throw new IllegalArgumentException("Invalid language pair format");
    }
}
