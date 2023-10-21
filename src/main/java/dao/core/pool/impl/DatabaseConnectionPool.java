package dao.core.pool.impl;

import dao.core.pool.ConnectionPool;
import dao.core.pool.ProxyConnection;

import javax.sql.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;

public class DatabaseConnectionPool implements ConnectionPool{

    private DatabaseConnectionPool() {
    }

    @Override
    public ProxyConnection getConnection() {
        return null;
    }

    @Override
    public void putBackConnection(ProxyConnection connection) {

    }

    @Override
    public void destroyPool() {

    }
}
