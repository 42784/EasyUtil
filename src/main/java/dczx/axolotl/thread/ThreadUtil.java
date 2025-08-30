package dczx.axolotl.thread;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/9/16 22:36
 * 简易的新线程操作工具
 * 这是一个性能低下的工具
 * 仅仅是适用非常小型的项目或不在意性能的情况下使用
 */
public class ThreadUtil {
    /**
     * 异步执行代码块
     * @param runnable 代码块
     */
    public static Thread runSync(Runnable runnable) {
        Thread thread = new Thread(runnable);
        thread.start();
        return thread;
    }

    /**
     * 异步执行代码块
     * @param runnable 代码块
     * @param name 线程名
     */
    public static Thread runSync(Runnable runnable, String name) {
        Thread thread = new Thread(runnable);
        thread.setName(name);
        thread.start();
        return thread;
    }
}
