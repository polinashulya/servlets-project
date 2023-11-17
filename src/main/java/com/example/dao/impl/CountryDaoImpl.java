package com.example.dao.impl;

import com.example.dao.ConnectionPool;
import com.example.dao.CountryDao;
import com.example.entity.Country;
import com.example.exception.DAOException;
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

public class CountryDaoImpl extends AbstractDao<Country> implements CountryDao {

    private static final Logger logger = LogManager.getLogger(CountryDaoImpl.class);

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

            statement = connection.prepareStatement("select c.id, c.name from countries c");
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

    private static Country getCountry(ResultSet set)  {
        try {
            return Country.builder()
                    .id(set.getLong(1))
                    .name(set.getString(2))
                    .build();
        } catch (SQLException e) {
            // Handle the exception or rethrow as a runtime exception
            throw new RuntimeException("Error mapping Country from ResultSet", e);
        }
    }


    @Override
    public Country getById(Long id) {

        String sql = "SELECT c.id, c.name FROM countries c where c.id = ? ";

        return super.getById(sql, id, CountryDaoImpl::getCountry);

    }

    @Override
    public Optional<Country> findById(Long id) {

        String sql = "SELECT c.id, c.name FROM countries c where c.id = ? ";

        return super.findById(sql, id, CountryDaoImpl::getCountry);
    }

}
