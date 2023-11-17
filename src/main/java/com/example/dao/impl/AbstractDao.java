package com.example.dao.impl;

import com.example.dao.ConnectionPool;
import com.example.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

import static com.example.dao.impl.DaoHelper.closeConnection;

public abstract class AbstractDao<T> {

    private final ConnectionPool connectionPool;

    public AbstractDao() {
        connectionPool = new ConnectionPool();
        connectionPool.init();
    }

    public T getById(String sql, Long id, Function<ResultSet, T> function) {
        Connection connection = null;
        PreparedStatement statement = null;

        T t = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setLong(1, id);

            ResultSet set = statement.executeQuery();

            if (set.next()) {
                t = function.apply(set);
            } else {
                // logger.warn("No user found by id " + id);
            }

        } catch (SQLException ex) {
            //logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            throw new DAOException(ex);
        } finally {
            closeConnection(connection, statement);
        }

        return t;
    }


}
