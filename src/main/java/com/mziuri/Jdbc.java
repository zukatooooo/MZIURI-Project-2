package com.mziuri;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Jdbc implements JdbcInterface {

    @Override
    public void createDatabase(String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306";
            Connection connection = DriverManager.getConnection(url, username, password);

            String userTable = "CREATE TABLE `mp2`.`user` (\n" +
                    "  `name` VARCHAR(45) NOT NULL,\n" +
                    "  `password` VARCHAR(45) NOT NULL);";
            String messageTable = "CREATE TABLE `mp2`.`message` (\n" +
                    "  `recipient` VARCHAR(45) NOT NULL,\n" +
                    "  `message` VARCHAR(45) NOT NULL);";
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP SCHEMA IF EXISTS mp2");
            statement.executeUpdate("CREATE SCHEMA mp2");
            statement.execute(userTable);
            statement.execute(messageTable);


            connection.close();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
