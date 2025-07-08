package net.todream.uni_translate.uni_translate.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * MyBatis 自定义类型处理器
 * 用于处理 Java Object 类型与数据库 JSON 字符串之间的转换
 * 
 * 功能：
 * 1. 将 Java Object 转换为 JSON 字符串存储到数据库
 * 2. 从数据库读取 JSON 字符串并转换为 Java Object
 * 3. 支持复杂的 JSON 对象结构
 * 
 * @author uni_translate
 */
@MappedTypes(Object.class)
public class ObjectTypeHandler extends BaseTypeHandler<Object> {
    
    /**
     * Jackson ObjectMapper 实例，用于 JSON 序列化和反序列化
     * 使用静态实例提高性能，避免重复创建
     */
    private static final ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * 将 Java Object 转换为 JSON 字符串并设置到 PreparedStatement 中
     * 当 MyBatis 执行 INSERT 或 UPDATE 操作时调用此方法
     * 
     * @param ps PreparedStatement 对象
     * @param i 参数位置索引
     * @param parameter 要转换的 Java Object
     * @param jdbcType JDBC 类型（在此处理器中不使用）
     * @throws SQLException 如果 JSON 序列化失败
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        try {
            // 将 Object 转换为 JSON 字符串并设置到 PreparedStatement
            ps.setString(i, objectMapper.writeValueAsString(parameter));
        } catch (JsonProcessingException e) {
            // 如果 JSON 序列化失败，抛出 SQLException
            throw new SQLException("Error converting object to JSON", e);
        }
    }
    
    /**
     * 从 ResultSet 中按列名获取 JSON 字符串并转换为 Java Object
     * 当 MyBatis 执行 SELECT 操作时调用此方法
     * 
     * @param rs ResultSet 对象
     * @param columnName 列名
     * @return 转换后的 Java Object，如果值为 null 则返回 null
     * @throws SQLException 如果数据库操作失败
     */
    @Override
    public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
        // 从 ResultSet 中获取 JSON 字符串
        String value = rs.getString(columnName);
        // 解析 JSON 字符串为 Object
        return parseObject(value);
    }
    
    /**
     * 从 ResultSet 中按列索引获取 JSON 字符串并转换为 Java Object
     * 当 MyBatis 执行 SELECT 操作时调用此方法
     * 
     * @param rs ResultSet 对象
     * @param columnIndex 列索引
     * @return 转换后的 Java Object，如果值为 null 则返回 null
     * @throws SQLException 如果数据库操作失败
     */
    @Override
    public Object getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        // 从 ResultSet 中获取 JSON 字符串
        String value = rs.getString(columnIndex);
        // 解析 JSON 字符串为 Object
        return parseObject(value);
    }
    
    /**
     * 从 CallableStatement 中按列索引获取 JSON 字符串并转换为 Java Object
     * 当 MyBatis 执行存储过程时调用此方法
     * 
     * @param cs CallableStatement 对象
     * @param columnIndex 列索引
     * @return 转换后的 Java Object，如果值为 null 则返回 null
     * @throws SQLException 如果数据库操作失败
     */
    @Override
    public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        // 从 CallableStatement 中获取 JSON 字符串
        String value = cs.getString(columnIndex);
        // 解析 JSON 字符串为 Object
        return parseObject(value);
    }
    
    /**
     * 解析 JSON 字符串为 Java Object
     * 包含错误处理和容错机制
     * 
     * @param value JSON 字符串
     * @return 解析后的 Java Object，如果解析失败则返回原始字符串
     */
    private Object parseObject(String value) {
        // 如果值为 null 或空字符串，直接返回 null
        if (value == null || value.trim().isEmpty()) {
            return null;
        }
        try {
            // 尝试将 JSON 字符串解析为 Object
            return objectMapper.readValue(value, Object.class);
        } catch (JsonProcessingException e) {
            // 如果 JSON 解析失败，直接返回原始字符串
            // 这样可以保证数据的完整性，避免因格式问题导致数据丢失
            return value;
        }
    }
} 