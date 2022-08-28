package com.lagou.edu.factory;

import com.lagou.edu.utils.ConnectionUtils;

/**
 * @author Jaa
 * @date 2021/12/23 0:13
 */
public class CreateBeanFactory {

    public static ConnectionUtils getInstanceStatic() {
        return new ConnectionUtils();
    }

    public ConnectionUtils getInstance() {
        return new ConnectionUtils();
    }
}
