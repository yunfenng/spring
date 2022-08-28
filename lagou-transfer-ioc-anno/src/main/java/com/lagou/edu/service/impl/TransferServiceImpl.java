package com.lagou.edu.service.impl;

import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.dao.impl.JdbcAccountDaoImpl;
import com.lagou.edu.pojo.Account;
import com.lagou.edu.service.TransferService;
import com.lagou.edu.utils.ConnectionUtils;
import com.lagou.edu.utils.TransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author 应癫
 */
@Service("transferService")
public class TransferServiceImpl implements TransferService {

    // 最佳状态
    // @Autowired 按照类型注入, 如果按照类型注入无法唯一锁定对象, 可以结合 @Qualifier
    @Autowired
    @Qualifier("accountDao")
    private AccountDao accountDao;

    // 构造函数传值/set方法传值
    // public void setAccountDao(AccountDao accountDao) {
    //     this.accountDao = accountDao;
    // }

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {

        try {
            // 开启事务(关闭事务的自动提交)
            // TransactionManager.getInstance().beginTransaction();

            Account from = accountDao.queryAccountByCardNo(fromCardNo);
            Account to = accountDao.queryAccountByCardNo(toCardNo);

            from.setMoney(from.getMoney() - money);
            to.setMoney(to.getMoney() + money);

            accountDao.updateAccountByCardNo(to);
            // int i = 1 / 0;
            accountDao.updateAccountByCardNo(from);

            // 提交事务
            // TransactionManager.getInstance().commitTransaction();
        } catch (Exception e) {
            e.printStackTrace();
            // 回滚事务
            // TransactionManager.getInstance().rollbackTransaction();

            // 抛出异常,便于上层servlet捕获
            throw e;
        }
    }
}
