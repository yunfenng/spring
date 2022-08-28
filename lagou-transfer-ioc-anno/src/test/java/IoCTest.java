import com.lagou.edu.SpringConfig;
import com.lagou.edu.dao.AccountDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author Jaa
 * @date 2021/12/21 7:33
 */
public class IoCTest {

    @Test
    public void testIoC() throws Exception {
        // 通过读取classpath下的xml文件来启动容器 (xml模式SE应用下推荐)
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);

        AccountDao accountDao = (AccountDao) applicationContext.getBean("accountDao");

        System.out.println(accountDao);
    }
}
