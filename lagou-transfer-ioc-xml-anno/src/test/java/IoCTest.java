import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.service.TransferService;
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

        // Object companyBean = applicationContext.getBean("companyBean");
        Object companyBean = applicationContext.getBean("&companyBean");
        System.out.println("===========companyBean:" + companyBean);

        AccountDao accountDao1 = (AccountDao) applicationContext.getBean("accountDao");
        accountDao1.queryAccountByCardNo("111111111");
        System.out.println(accountDao1);

        AccountDao accountDao2 = (AccountDao) applicationContext.getBean("accountDao");
        System.out.println(accountDao2);


        Object connectionUtils = applicationContext.getBean("connectionUtils");
        System.out.println(connectionUtils);

        applicationContext.close();
    }

    /**
     * 测试bean对象的延迟加载
     */
    @Test
    public void testBeanLazy() {
        // 启动容器(容器初始化)
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // getBean获取bean对象使用
        Object lazyResult = applicationContext.getBean("lazyResult");
        System.out.println(lazyResult);
        applicationContext.close();
    }

    /**
     * 测试 xml aop
     */
    @Test
    public void testXmlAop() throws Exception {
        // 启动容器(容器初始化)
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // getBean获取bean对象使用
        TransferService transferService = applicationContext.getBean(TransferService.class);
        transferService.transfer("6029621011000", "6029621011001", 100);
    }

    /**
     * 测试 xml anno aop
     */
    @Test
    public void testXmlAnnoAop() throws Exception {
        // 启动容器(容器初始化)
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        // getBean获取bean对象使用
        TransferService transferService = applicationContext.getBean(TransferService.class);
        transferService.transfer("6029621011000", "6029621011001", 100);
    }
}
