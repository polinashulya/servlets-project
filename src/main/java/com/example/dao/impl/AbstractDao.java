package com.example.dao.impl;

import com.example.dao.ConnectionPool;
import com.example.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.function.Function;

import static com.example.dao.impl.DaoHelper.closeConnection;

public abstract class AbstractDao<T> {

    private static final Logger logger = LogManager.getLogger(AbstractDao.class);

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

            logger.info(set);

            if (set.next()) {
                t = function.apply(set);
            } else {
                 logger.warn("No item found by id " + id);
            }

        } catch (SQLException ex) {
            logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            throw new DAOException(ex);
        } finally {
            closeConnection(connection, statement);
        }

        return t;
    }

    public T getByLogin(String sql, String login, Function<ResultSet, T> function) {
        Connection connection = null;
        PreparedStatement statement = null;

        T t = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, login);

            ResultSet set = statement.executeQuery();
            logger.debug("Executing query: {}", statement.toString());

            if (set.next()) {
                t = function.apply(set);
            } else {
                logger.warn("No item found with by login " + login);
            }

        } catch (SQLException ex) {
            logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            throw new DAOException(ex);
        } finally {
            closeConnection(connection, statement);
        }

        return t;
    }

    public Optional<T> findById(String sql, Long id, Function<ResultSet, T> function) {
        return Optional.ofNullable(getById(sql, id, function));
    }


    public Optional<T> findByLogin(String sql, String login, Function<ResultSet, T> function){
        return Optional.ofNullable(getByLogin(sql, login, function));
    }

    public void delete(String deleteSql, String findSql, Long id, Function<ResultSet, T> function) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            if (findById(findSql, id, function).isPresent()) {
                connection = connectionPool.getConnection();
                statement = connection.prepareStatement(deleteSql);

                statement.setLong(1, id);

                statement.executeUpdate();
                logger.debug("Executing query: {}", statement.toString());

            } else {
                logger.debug("Item with id = " + id + " was not found!");
                throw new DAOException("Item with id = " + id + " was not found!");
            }
        } catch (SQLException ex) {
            logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            System.err.println();
            throw new DAOException(ex);
        } finally {
            closeConnection(connection, statement);
        }

    }

}
