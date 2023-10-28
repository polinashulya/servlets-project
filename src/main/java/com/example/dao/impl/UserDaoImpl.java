package com.example.dao.impl;

import com.example.entity.User;
import com.example.dao.ConnectionPool;
import com.example.dao.UserDao;
import com.example.exception.DAOException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static final String sql = "select id,login, password, first_name, second_name, birth_date, banned, deleted from users WHERE deleted='false';";
    private final ConnectionPool connectionPool;

    public UserDaoImpl() {
        connectionPool = new ConnectionPool();
        connectionPool.init();
    }

    @Override
    public List<User> findAll()  {
        Connection connection = null;
        PreparedStatement statement = null;

        List<User> users = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();
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
    public User getById(long id)  {
        Connection connection = null;
        PreparedStatement statement = null;

        User user = null;
        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement("select id, first_name from users where id = ?;");

            statement.setLong(1, id);

            ResultSet set = statement.executeQuery();

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
    public Optional<User> findById(long id)  {
        return Optional.ofNullable(getById(id));
    }

    private static User getUser(ResultSet set) throws SQLException {
        return User.builder()
                .id(set.getLong(1))
                .login(set.getString(2))
                .password(set.getString(3))
                .firstName(set.getString(4))
                .secondName(set.getString(5))
                .birthDate((set.getDate(6)).toLocalDate())
                .banned(set.getBoolean(7))
                .build();
    }


    @Override
    public void save(User user)  {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement("INSERT INTO users (login, password, first_name, second_name, birth_date, banned, deleted ) VALUES (?,?,?,?,?,?,?);");

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getSecondName());
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
    public void delete(long id)  {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            if (findById(id).isPresent()) {
                statement = connection.prepareStatement("UPDATE users SET deleted=true where id = ?;");

                statement.setLong(1, id);

                int row = statement.executeUpdate();

            }

        } catch (SQLException ex) {
            System.err.println();
            throw new DAOException(ex);
        } finally {
            closeConnection(connection, statement);
        }

    }

    private static void closeConnection(Connection connection, PreparedStatement statement) {
        if (connection != null) {
            try {
                connection.close();
            } catch (Exception ex) {
                //todo should i throw org.exception or just log?
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
