package com.example.dao.impl;

import com.example.entity.User;
import com.example.dao.ConnectionPool;
import com.example.dao.UserDao;
import com.example.exception.DAOException;
import jakarta.servlet.http.HttpServletRequest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final String FIND_ALL_USERS = "select id, login, password, firstname, surname, birth_date, banned, deleted from users WHERE deleted='false';";
    static final String SORT_TYPE_ASC = "ASC";
    static final String SORT_USERS_BY_ID= "byId";
    static final String SORT_USERS_BY_SURNAME = "bySurname";
    static final String SORT_USERS_BY_LOGIN = "byLogin";
    static final String SORT_USERS_BY_BIRTH_DATE = "byBirthDate";
    private static final String GET_ALL_USERS_SORTED_BY_LOGIN_ASC =
            "SELECT " +
                    "u.id," +
                    "u.login," +
                    "u.firstname," +
                    "u.surname," +
                    "u.birth_date," +
                    "u.banned" +
                    " FROM users u" +
                    " ORDER BY login ASC";

    private static final String GET_ALL_USERS_SORTED_BY_LOGIN_DESC =
            "SELECT " +
                    "u.id," +
                    "u.login," +
                    "u.firstname," +
                    "u.surname," +
                    "u.birth_date," +
                    "u.banned" +
                    " FROM users u" +
                    " ORDER BY login DESC";

    private static final String GET_ALL_USERS_SORTED_BY_BIRTH_DATE_ASC =
            "SELECT " +
                    "u.id," +
                    "u.login," +
                    "u.firstname," +
                    "u.surname," +
                    "u.birth_date," +
                    "u.banned" +
                    " FROM users u" +
                    " ORDER BY birth_date ASC";

    private static final String GET_ALL_USERS_SORTED_BY_BIRTH_DATE_DESC =
            "SELECT " +
                    "u.id," +
                    "u.login," +
                    "u.firstname," +
                    "u.surname," +
                    "u.birth_date," +
                    "u.banned" +
                    " FROM users u" +
                    " ORDER BY birth_date DESC";

    private static final String GET_ALL_USERS_SORTED_BY_SURNAME_ASC =
            "SELECT " +
                    "u.id," +
                    "u.login," +
                    "u.firstname," +
                    "u.surname," +
                    "u.birth_date," +
                    "u.banned" +
                    " FROM users u" +
                    " ORDER BY surname DESC";

    private static final String GET_ALL_USERS_SORTED_BY_SURNAME_DESC =
            "SELECT " +
                    "u.id," +
                    "u.login," +
                    "u.firstname," +
                    "u.surname," +
                    "u.birth_date," +
                    "u.banned" +
                    " FROM Users u" +
                    " ORDER BY surname DESC";

    private static final String GET_ALL_USERS_SORTED_BY_ID_ASC =
            "SELECT " +
                    "u.id," +
                    "u.login," +
                    "u.firstname," +
                    "u.surname," +
                    "u.birth_date," +
                    "u.banned" +
                    " FROM users u" +
                    " ORDER BY id ASC";

    private static final String GET_ALL_USERS_SORTED_BY_ID_DESC =
            "SELECT " +
                    "u.id," +
                    "u.login," +
                    "u.firstname," +
                    "u.surname," +
                    "u.birth_date," +
                    "u.banned" +
                    " FROM users u" +
                    " ORDER BY id DESC";


    private final ConnectionPool connectionPool;

    public UserDaoImpl() {
        connectionPool = new ConnectionPool();
        connectionPool.init();
    }

    @Override
    public List<User> findAll(String sql) {
        Connection connection = null;
        PreparedStatement statement = null;

        List<User> users = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();

            if (sql.isEmpty()) {
                sql = FIND_ALL_USERS;
            }
            statement = connection.prepareStatement(sql);

            ResultSet set = statement.executeQuery();

            while (set.next()) {
                User user = getUser(set);

                users.add(user);
            }

        } catch (SQLException ex) {
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
            statement = connection.prepareStatement("select * from users where id = ?;");

            statement.setLong(1, id);

            ResultSet set = statement.executeQuery();
            System.out.println(set);
            if (set.next()) {
                user = getUser(set);
            }

        } catch (SQLException ex) {
            System.err.println();
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

    private static User getUser(ResultSet set) throws SQLException {
        return User.builder()
                .id(set.getLong(1))
                .login(set.getString(2))
                .firstname(set.getString(3))
                .surname(set.getString(4))
                .birthDate((set.getDate(5)).toLocalDate())
                .banned(set.getBoolean(6))
                .build();
    }


    @Override
    public void save(User user) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement("INSERT INTO users (login, password, firstname, surname, birth_date, banned, deleted ) VALUES (?,?,?,?,?,?,?);");

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstname());
            statement.setString(4, user.getSurname());
            statement.setDate(5, Date.valueOf(user.getBirthDate()));
            statement.setBoolean(6, user.isBanned());
            statement.setBoolean(7, user.isDeleted());

            statement.executeUpdate();

        } catch (SQLException ex) {
            System.err.println();
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
                statement = connection.prepareStatement("UPDATE users SET deleted=true where id = ?;");
                statement.setLong(1, id);

                statement.executeUpdate();
            } else {
                throw new DAOException("User with id = " + id + " was not found!");
            }
        } catch (SQLException ex) {
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
    public String getSortingSql(String sortBy, String sortType) {
        switch (getSortByOrDefault(sortBy)) {
            case SORT_USERS_BY_LOGIN -> {
                return SORT_TYPE_ASC.equals(sortType) ?
                        GET_ALL_USERS_SORTED_BY_LOGIN_ASC :
                        GET_ALL_USERS_SORTED_BY_LOGIN_DESC;
            }
            case SORT_USERS_BY_SURNAME -> {
                return SORT_TYPE_ASC.equals(sortType) ?
                        GET_ALL_USERS_SORTED_BY_SURNAME_ASC :
                        GET_ALL_USERS_SORTED_BY_SURNAME_DESC;
            }
            case SORT_USERS_BY_BIRTH_DATE -> {
                return SORT_TYPE_ASC.equals(sortType) ?
                        GET_ALL_USERS_SORTED_BY_BIRTH_DATE_ASC :
                        GET_ALL_USERS_SORTED_BY_BIRTH_DATE_DESC;
            } case SORT_USERS_BY_ID -> {
                return SORT_TYPE_ASC.equals(sortType) ?
                        GET_ALL_USERS_SORTED_BY_ID_ASC :
                        GET_ALL_USERS_SORTED_BY_ID_DESC;
            }
            default -> {
                return GET_ALL_USERS_SORTED_BY_ID_ASC;
            }
        }
    }

    private static void closeConnection(Connection connection, PreparedStatement statement) {
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
