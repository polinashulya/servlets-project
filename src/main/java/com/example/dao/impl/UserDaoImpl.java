package com.example.dao.impl;

import com.example.dao.ConnectionPool;
import com.example.dao.UserDao;
import com.example.entity.Country;
import com.example.entity.User;
import com.example.exception.DAOException;
import com.example.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.dao.impl.DaoHelper.closeConnection;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    private static final String FIND_ALL_USERS =
            "SELECT u.id, u.login, u.firstname, u.surname, u.birth_date, u.banned, u.country_id, c.name " +
                    "FROM users u " +
                    "join countries c on u.country_id = c.id " +
                    "WHERE u.deleted = 'false' ";
    private static final String SORT_TYPE_ASC = "ASC";
    private static final String SORT_USERS_BY_ID = "byId";
    private static final String SORT_USERS_BY_SURNAME = "bySurname";
    private static final String SORT_USERS_BY_LOGIN = "byLogin";
    private static final String SORT_USERS_BY_BIRTH_DATE = "byBirthDate";

    private static final String PAGINATION_DEFAULT = " LIMIT " + 5 + " OFFSET " + 0;

    private static final String TOTAL_COUNT_USERS =
            "SELECT COUNT(*) AS totalUsers " +
                    "FROM users u " +
                    "join countries c on u.country_id = c.id " +
                    "WHERE u.deleted = 'false' ";


    private final ConnectionPool connectionPool;

    public UserDaoImpl() {
        connectionPool = new ConnectionPool();
        connectionPool.init();
    }

    @Override
    public List<User> findAll(String filterAndSearchsql, String sortSql, String page, String pageSize) {
        Connection connection = null;
        PreparedStatement statement = null;

        String paginationSql;
        int offset = 0;

        if (page != null && !page.isEmpty() && pageSize != null && !pageSize.isEmpty()) {

            offset = (Integer.parseInt(page) - 1) * Integer.parseInt(pageSize);
            paginationSql = " LIMIT " + pageSize + " OFFSET " + offset;

        } else {
            paginationSql = PAGINATION_DEFAULT;
        }


        List<User> users = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();

            statement = connection.prepareStatement(FIND_ALL_USERS + filterAndSearchsql + sortSql + paginationSql);

            ResultSet set = statement.executeQuery();
            logger.debug("Executing query: {}", statement.toString());

            while (set.next()) {
                User user = getUser(set);

                users.add(user);
            }


        } catch (SQLException ex) {
            logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            throw new DAOException(ex);
        } finally {
            closeConnection(connection, statement);
        }

        return users;
    }

    @Override
    public User getById(long id) {
        Connection connection = null;
        PreparedStatement statement = null;

        User user = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement("SELECT u.id, u.login, u.firstname, u.surname, u.birth_date, u.banned, u.country_id, c.name " +
                    "FROM users u join countries c on u.country_id = c.id where u.id = ?;");

            statement.setLong(1, id);

            ResultSet set = statement.executeQuery();
            logger.debug("Executing query: {}", statement.toString());

            if (set.next()) {
                user = getUser(set);
            } else {
                logger.warn("No user found by id " + id);
            }

        } catch (SQLException ex) {
            logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            throw new DAOException(ex);
        } finally {
            closeConnection(connection, statement);
        }

        return user;
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(getById(id));
    }

    @Override
    public User getByLogin(String login) {
        Connection connection = null;
        PreparedStatement statement = null;

        User user = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement("SELECT u.id, u.login, u.firstname, u.surname, u.birth_date, u.banned, u.country_id, c.name " +
                    "FROM users u join countries c on u.country_id = c.id where u.login = ?;");

            statement.setString(1, login);

            ResultSet set = statement.executeQuery();
            logger.debug("Executing query: {}", statement.toString());

            if (set.next()) {
                user = getUser(set);
            } else {
                logger.warn("No user found with by login " + login);
            }

        } catch (SQLException ex) {
            logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            throw new DAOException(ex);
        } finally {
            closeConnection(connection, statement);
        }

        return user;
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return Optional.ofNullable(getByLogin(login));
    }

    private static User getUser(ResultSet set) throws SQLException {
        return User.builder()
                .id(set.getLong(1))
                .login(set.getString(2))
                .firstname(set.getString(3))
                .surname(set.getString(4))
                .birthDate((set.getDate(5)).toLocalDate())
                .banned(set.getBoolean(6))
                .country(
                        Country.builder()
                                .id(set.getLong(7))
                                .name(set.getString(8))
                                .build()
                )
                .build();
    }


    @Override
    public void save(User user) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement("INSERT INTO users (login, password, firstname, surname, birth_date, banned, deleted, country_id ) VALUES (?,?,?,?,?,?,?, ?);");

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstname());
            statement.setString(4, user.getSurname());
            statement.setDate(5, Date.valueOf(user.getBirthDate()));
            statement.setBoolean(6, user.isBanned());
            statement.setBoolean(7, user.isDeleted());
            statement.setLong(8, user.getCountry().getId());

            statement.executeUpdate();
            logger.debug("Executing query: {}", statement.toString());

        } catch (SQLException ex) {
            logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            throw new DAOException(ex);
        } finally {
            closeConnection(connection, statement);
        }

    }

    @Override
    public void delete(long id) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            if (findById(id).isPresent()) {
                connection = connectionPool.getConnection();
                statement = connection.prepareStatement("UPDATE users u SET deleted=true where u.id = ?;");
                statement.setLong(1, id);

                statement.executeUpdate();
                logger.debug("Executing query: {}", statement.toString());

            } else {
                logger.debug("User with id = " + id + " was not found!");
                throw new DAOException("User with id = " + id + " was not found!");
            }
        } catch (SQLException ex) {
            logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            System.err.println();
            throw new DAOException(ex);
        } finally {
            closeConnection(connection, statement);
        }

    }

    private static String getSortByOrDefault(String sortBy) {
        return sortBy == null ? "default" : sortBy;
    }

    @Override
    public String getFilterAndSearchSql(String countryId, String search) {

        String sql = new String();
        String filterSql = new String();
        String searchSql = new String();

        if (countryId != null && !countryId.isEmpty()) {
            filterSql = " AND u.country_id = " + countryId;
        }

        if (search != null && !search.isEmpty()) {
            searchSql = " AND (u.login LIKE '%" + search + "%'" +
                    " OR u.firstname LIKE '%" + search + "%'" +
                    " OR u.surname LIKE '%" + search + "%'" +
                    " OR c.name LIKE '%" + search + "%')";
        }

        sql = filterSql + searchSql;

        return sql;
    }

    @Override
    public String getSortingSql(String sortBy, String sortType) {

        switch (getSortByOrDefault(sortBy)) {
            case SORT_USERS_BY_LOGIN -> {
                return SORT_TYPE_ASC.equals(sortType) ?
                        " ORDER BY u.login ASC" :
                        " ORDER BY u.login DESC";
            }
            case SORT_USERS_BY_SURNAME -> {
                return SORT_TYPE_ASC.equals(sortType) ?
                        " ORDER BY u.surname ASC" :
                        " ORDER BY u.surname DESC";
            }
            case SORT_USERS_BY_BIRTH_DATE -> {
                return SORT_TYPE_ASC.equals(sortType) ?
                        " ORDER BY u.birth_date ASC" :
                        " ORDER BY u.birth_date DESC";
            }
            case SORT_USERS_BY_ID -> {
                return SORT_TYPE_ASC.equals(sortType) ?
                        " ORDER BY u.id ASC" :
                        " ORDER BY u.id DESC";
            }
            default -> {
                return " ORDER BY u.id ASC";
            }
        }
    }

    @Override
    public int getTotalResult(String filterAndSearchsql) {
        Connection connection = null;
        PreparedStatement statement = null;

        int totalResult = 0;

        try {
            connection = connectionPool.getConnection();

            statement = connection.prepareStatement(TOTAL_COUNT_USERS + filterAndSearchsql);

            ResultSet set = statement.executeQuery();
            logger.debug("Executing query: {}", statement.toString());

            if (set.next()) {
                totalResult = set.getInt("totalUsers");
            }  else {
            logger.warn("The total result of users is null");
        }

        } catch (SQLException ex) {
            logger.error("An SQL exception occurred: {}", ex.getMessage(), ex);
            throw new DAOException(ex);
        } finally {
            closeConnection(connection, statement);
        }

        return totalResult;
    }

}
