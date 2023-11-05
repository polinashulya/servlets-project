package com.example.dao.impl;

import com.example.dao.ConnectionPool;
import com.example.dao.UserDao;
import com.example.entity.Country;
import com.example.entity.User;
import com.example.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.dao.impl.DaoHelper.closeConnection;

public class UserDaoImpl implements UserDao {

    private static final String FIND_ALL_USERS =
            "SELECT u.id, u.login, u.firstname, u.surname, u.birth_date, u.banned, u.country_id, c.name " +
                    "FROM users u " +
                    "join countries c on u.country_id = c.id " +
                    "WHERE u.deleted = 'false' ";
    static final String SORT_TYPE_ASC = "ASC";
    static final String SORT_USERS_BY_ID = "byId";
    static final String SORT_USERS_BY_SURNAME = "bySurname";
    static final String SORT_USERS_BY_LOGIN = "byLogin";
    static final String SORT_USERS_BY_BIRTH_DATE = "byBirthDate";


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
            statement = connection.prepareStatement("SELECT u.id, u.login, u.firstname, u.surname, u.birth_date, u.banned, u.country_id " +
                    "FROM users u join countries c on u.country_id = c.id where id = ?;");

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
    public String getSortingAndFilteringSql(String sortBy, String sortType, String countryId) {

        String filteringSql = " ";

        if (countryId != null && !countryId.isEmpty()) {
            filteringSql = "AND u.country_id = " + countryId;
        }

        switch (getSortByOrDefault(sortBy)) {
            case SORT_USERS_BY_LOGIN -> {
                return SORT_TYPE_ASC.equals(sortType) ?
                        FIND_ALL_USERS + filteringSql + " ORDER BY u.login ASC" :
                        FIND_ALL_USERS + filteringSql + " ORDER BY u.login DESC";
            }
            case SORT_USERS_BY_SURNAME -> {
                return SORT_TYPE_ASC.equals(sortType) ?
                        FIND_ALL_USERS + filteringSql + " ORDER BY u.surname ASC" :
                        FIND_ALL_USERS + filteringSql + " ORDER BY u.surname DESC";
            }
            case SORT_USERS_BY_BIRTH_DATE -> {
                return SORT_TYPE_ASC.equals(sortType) ?
                        FIND_ALL_USERS + filteringSql + " ORDER BY u.birth_date ASC" :
                        FIND_ALL_USERS + filteringSql + " ORDER BY u.birth_date DESC";
            }
            case SORT_USERS_BY_ID -> {
                return SORT_TYPE_ASC.equals(sortType) ?
                        FIND_ALL_USERS + filteringSql + " ORDER BY u.id ASC" :
                        FIND_ALL_USERS + filteringSql + " ORDER BY u.id DESC";
            }
            default -> {
                return FIND_ALL_USERS + filteringSql + " ORDER BY u.id ASC";
            }
        }
    }


}
