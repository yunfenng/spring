package aop;

/**
 * @author Jaa
 * @date 2021/12/11 16:27
 */
public class Animal {
    private String height;  // 高度
    private float weight;   // 体重

    public void eat() {
        // 性能测试代码
        long start = System.currentTimeMillis();
        // 业务逻辑代码
        System.out.println("I can eat...");
        // 性能测试代码
        long end = System.currentTimeMillis();
        System.out.println("执行时长：" + (end - start) / 1000f + "s");
    }

    public void run() {
        // 性能测试代码
        long start = System.currentTimeMillis();
        // 业务逻辑代码
        System.out.println("I can run...");
        // 性能测试代码
        long end = System.currentTimeMillis();
        System.out.println("执行时长：" + (end - start) / 1000f + "s");

    }
}
