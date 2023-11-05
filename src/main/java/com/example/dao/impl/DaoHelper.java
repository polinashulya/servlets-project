package com.example.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class DaoHelper {

    static void closeConnection(Connection connection, PreparedStatement statement) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception ex) {

            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (Exception ex) {

            }
        }
    }
}
