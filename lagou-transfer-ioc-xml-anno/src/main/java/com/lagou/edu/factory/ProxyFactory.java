package com.lagou.edu.factory;

import com.lagou.edu.pojo.Account;
import com.lagou.edu.utils.TransactionManager;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Jaa
 * @date 2021/12/15 22:35
 */
@Component("proxyFactory")
public class ProxyFactory {

    @Autowired
    private TransactionManager transactionManager;

    // public void setTransactionManager(TransactionManager transactionManager) {
    //     this.transactionManager = transactionManager;
    // }

    /*private ProxyFactory() {

    }

    private static ProxyFactory proxyFactory = new ProxyFactory();

    public static ProxyFactory getInstance() {
        return proxyFactory;
    }*/

    /**
     * Jdk动态代理
     * @param obj  委托对象
     * @return   代理对象
     */
    public Object getJdkProxy(Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(),
                new InvocationHandler() {
                    Object result = null;

                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        try {
                            // 开启事务(关闭事务的自动提交)
                            transactionManager.beginTransaction();

                            result = method.invoke(obj, args);

                            // 提交事务
                            transactionManager.commitTransaction();
                        } catch (Exception e) {
                            e.printStackTrace();
                            // 回滚事务
                            transactionManager.rollbackTransaction();

                            // 抛出异常,便于上层servlet捕获
                            throw e;
                        }
                        return result;
                    }
                }
        );
    }

    /**
     * 使用cglib动态代理生成代理对象
     * @param obj 委托对象
     * @return
     */
    public Object getCglibProxy(Object obj) {
        return  Enhancer.create(obj.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                Object result = null;
                try {
                    // 开启事务(关闭事务的自动提交)
                    transactionManager.beginTransaction();

                    result = method.invoke(obj, objects);

                    // 提交事务
                    transactionManager.commitTransaction();
                } catch (Exception e) {
                    e.printStackTrace();
                    // 回滚事务
                    transactionManager.rollbackTransaction();

                    // 抛出异常,便于上层servlet捕获
                    throw e;
                }
                return result;
            }
        });
    }
}
