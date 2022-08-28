package com.lagou.edu.utils;

import java.sql.SQLException;

/**
 * @author Jaa
 * @date 2021/12/14 22:32
 */
public class TransactionManager {

    private ConnectionUtils connectionUtils;

    public void setConnectionUtils(ConnectionUtils connectionUtils) {
        this.connectionUtils = connectionUtils;
    }

    /*private TransactionManager() {

    }

    private static TransactionManager transactionManager = new TransactionManager();

    public static TransactionManager getInstance() {
        return transactionManager;
    }*/

    // 开启事务
    public void beginTransaction() throws SQLException {
        connectionUtils.getCurThreadConn().setAutoCommit(false);
    }

    // 提交事务
    public void commitTransaction() throws SQLException {
        connectionUtils.getCurThreadConn().commit();
    }

    // 回滚事务
    public void rollbackTransaction() throws SQLException {
        connectionUtils.getCurThreadConn().rollback();
    }
}
