package com.lijq.mybatis.v1;

import com.lijq.mybatis.biz.User;

import java.sql.*;

/**
 * @author Lijq
 * @date 2018/4/2 11:37
 * @description
 */
public class SimpleExecutor implements Executor {


    @SuppressWarnings("unchecked")
    @Override
    public <T> T query(String sql, String parameter) {

        Connection connection = null;
        PreparedStatement preparedStatement;
        User user = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/study" +
                            "?useUnicode=true&characterEncoding=utf-8&useSSL=false&useJDBCCompliantTimezoneShift=true" +
                            "&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");
            sql = String.format(sql, Integer.parseInt(parameter));
            preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUsername(rs.getString("username"));
                user.setAge(rs.getInt("age"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return (T) user;

    }
}
