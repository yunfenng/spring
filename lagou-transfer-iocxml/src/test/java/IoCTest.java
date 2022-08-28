import com.lagou.edu.dao.AccountDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author Jaa
 * @date 2021/12/21 7:33
 */
public class IoCTest {

    @Test
    public void testIoC() throws Exception {
        // 通过读取classpath下的xml文件来启动容器 (xml模式SE应用下推荐)
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        // 不推荐使用
        // ApplicationContext applicationContext1 = new FileSystemXmlApplicationContext("文件系统的绝对路径");

        AccountDao accountDao1 = (AccountDao) applicationContext.getBean("accountDao");
        accountDao1.queryAccountByCardNo("111111111");
        System.out.println(accountDao1);

        AccountDao accountDao2 = (AccountDao) applicationContext.getBean("accountDao");
        System.out.println(accountDao2);


        Object connectionUtils = applicationContext.getBean("connectionUtils");
        System.out.println(connectionUtils);

        applicationContext.close();
    }
}
