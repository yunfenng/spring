package com.lagou.edu.utils;

import com.alibaba.druid.pool.DruidPooledConnection;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Jaa
 * @date 2021/12/14 22:03
 */
public class ConnectionUtils {

    // 单例
    /*private ConnectionUtils() {

    }

    private static ConnectionUtils connectionUtils = new ConnectionUtils();

    public static ConnectionUtils getInstance() {
        return connectionUtils;
    }*/

    private ThreadLocal<Connection> threadLocal = new ThreadLocal<>(); // 存储当前线程的连接

    /**
     * 从当前线程中获取连接
     * @return
     */
    public Connection getCurThreadConn() throws SQLException {
        /**
         * 判断当前线程是否绑定了连接,如果没有绑定, 需要从连接池中获取一个连接绑定到当前线程
         */
        Connection connection = threadLocal.get();
        if (connection == null) {
            // 从连接池中拿连接绑定到当前线程
            connection = DruidUtils.getInstance().getConnection();
            threadLocal.set(connection);
        }
        return connection;
    }
}
