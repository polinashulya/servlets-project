package com.example.dao.impl;

import com.example.dao.ConnectionPool;
import com.example.dao.CountryDao;
import com.example.entity.Country;
import com.example.exception.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.example.dao.impl.DaoHelper.closeConnection;

public class CountryDaoImpl implements CountryDao {

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

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                Country country = getCountry(set);

                countries.add(country);
            }

        } catch (SQLException ex) {
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
}
