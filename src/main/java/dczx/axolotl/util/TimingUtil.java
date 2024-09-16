package dczx.axolotl.util;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/9/16 22:42
 */
public class TimingUtil {
    /**
     * 获取执行代码块的用时
     *
     * @param runnable 代码块
     * @return 执行耗时(ms)
     */
    public static long useTime(Runnable runnable) {
        long start = System.currentTimeMillis();
        runnable.run();
        return System.currentTimeMillis() - start;
    }
}
