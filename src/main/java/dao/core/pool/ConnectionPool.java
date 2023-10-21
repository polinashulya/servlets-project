package dao.core.pool;

public interface ConnectionPool {

    ProxyConnection getConnection();

    void putBackConnection(ProxyConnection connection);

    void destroyPool();
}