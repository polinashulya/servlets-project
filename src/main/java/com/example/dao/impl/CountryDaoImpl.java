package com.example.dao.impl;

import com.example.dao.ConnectionPool;
import com.example.dao.CountryDao;
import com.example.entity.Country;
import com.example.entity.User;
import com.example.exception.DAOException;
import com.example.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.dao.impl.DaoHelper.closeConnection;

public class CountryDaoImpl implements CountryDao {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private final ConnectionPool connectionPool;

    public CountryDaoImpl() {
        connectionPool = new ConnectionPool();
        connectionPool.init();
    }

    @Override
    public List<Country> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;

        List<Country> countries = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();

            statement = connection.prepareStatement("select * from countries");
            logger.debug("Executing query: {}", statement.toString());

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                Country country = getCountry(set);

                countries.add(country);
            }

        } catch (SQLException ex) {
            logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            throw new DAOException(ex);
        } finally {
            closeConnection(connection, statement);
        }

        return countries;
    }

    private Country getCountry(ResultSet set) throws SQLException {
        return Country.builder()
                .id(set.getLong(1))
                .name(set.getString(2))
                .build();
    }

    @Override
    public Country getById(long id) {
        Connection connection = null;
        PreparedStatement statement = null;

        Country country = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement("SELECT c.id, c.name FROM countries c where c.id = ?;");

            statement.setLong(1, id);
            logger.debug("Executing query: {}", statement.toString());

            ResultSet set = statement.executeQuery();

            System.out.println(set);
            if (set.next()) {
                country = getCountry(set);
            }

        } catch (SQLException ex) {
            logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            throw new DAOException(ex);
        } finally {
            closeConnection(connection, statement);
        }

        return country;
    }

    @Override
    public Optional<Country> findById(long id) {
        return Optional.ofNullable(getById(id));
    }

}
