package dczx.axolotl.thread;

import lombok.Getter;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/8/30 11:13
 * <p>
 * 虚拟线程工具
 */
public class VirtualThreadUtil {
    @Getter
    private static ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

    public static void newExecutor() {
        executor.shutdown();
        executor = Executors.newVirtualThreadPerTaskExecutor();
    }

    public static void submit(Runnable runnable) {
        executor.submit(runnable);
    }

    public static <T> Future<T> submit(Callable<T> task) {
        return executor.submit(task);
    }

    public static void shutdown() {
        executor.shutdown();
    }

    public static boolean isShutdown() {
        return executor.isShutdown();
    }

    public static boolean isTerminated() {
        return executor.isTerminated();
    }

    public static boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return executor.awaitTermination(timeout, unit);
    }

    public static void shutdownNow() {
        executor.shutdownNow();
    }

    public static <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException {
        return executor.invokeAll(tasks);
    }

    public static <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) throws InterruptedException {
        return executor.invokeAll(tasks, timeout, unit);
    }
}
