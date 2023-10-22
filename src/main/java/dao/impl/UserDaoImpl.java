package dao.impl;

import dao.ConnectionPool;
import dao.UserDao;
import entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final String sql = "select id, first_name from users;";
    private final ConnectionPool connectionPool;

    public UserDaoImpl() {
        connectionPool = new ConnectionPool();
        connectionPool.init();
    }

    @Override
    public List<User> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;

        List<User> users = new ArrayList<>();

        try {
            connection = connectionPool.getConnection();
            statement = connection.prepareStatement(sql);

            ResultSet set = statement.executeQuery();

            while(set.next()) {
                // todo should it be separate method to be reused by getById ?
                User user = getUser(set);

                users.add(user);
            }

        } catch (SQLException ex) {
            System.err.println(); //todo should i use exceptions here?
        } finally { // todo how can the finally block be optimized to be reuzed between  multiple  method or even multiple daos?
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                    //todo should i throw exception or just log?
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception ex) {

                }
            }
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
            statement = connection.prepareStatement("select id, first_name from users where id = ?;");

            statement.setLong(1, id);

            ResultSet set = statement.executeQuery();

            if(set.next()) {
                // todo should it be separate method to be reused by getById ?
                user = getUser(set);
            }

        } catch (SQLException ex) {
            System.err.println(); //todo should i use exceptions here?
        } finally { // todo how can the finally block be optimized to be reuzed between  multiple  method or even multiple daos?
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception ex) {
                    //todo should i throw exception or just log?
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception ex) {

                }
            }
        }

        return user;
    }

    private static User getUser(ResultSet set) throws SQLException {
        return User.builder()
                .id(set.getLong(1))
                .firstName(set.getString(2))
                .build();
    }

    @Override
    public void save(User user) {
//        Connection connection = null;
//        PreparedStatement statement = null;
//
//        List<User> users = new ArrayList<>();
//
//        try {
//            connection = connectionPool.getConnection();
//            statement = connection.prepareStatement("INSERT INTO users (login, password, first_name, second_name, birth_date, banned ) VALUES (?,?,?);");
//
//
//            statement.setString(2, user.getLogin());
//            statement.setString(3, user.getPassword());
//            statement.setString(4, user.getFirstName());
//            statement.setString(5, user.getSurname());
//            statement.setDate(6, Date.valueOf(user.getBirthDate()));
//            statement.setBoolean(7, user.isBanned());
//
//            ResultSet row = statement.executeQuery();
//
//        } catch (SQLException ex) {
//            System.err.println(); //todo should i use exceptions here?
//        } finally { // todo how can the finally block be optimized to be reuzed between  multiple  method or even multiple daos?
//            if (connection != null) {
//                try {
//                    connection.close();
//                } catch (Exception ex) {
//                    //todo should i throw exception or just log?
//                }
//            }
//            if (statement != null) {
//                try {
//                    statement.close();
//                } catch (Exception ex) {
//
//                }
//            }
//        }

    }

    @Override
    public void delete(long id) {

    }
}
