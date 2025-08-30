package dczx.axolotl.util;

import dczx.axolotl.thread.VirtualThreadUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/8/30 16:06
 */
class SystemOperationUtilTest {

    @Test
    @SneakyThrows
    void pasteClipboard() {
        SystemOperationUtil.pasteText("测试A");
        SystemOperationUtil.pasteText("测试B");
        SystemOperationUtil.pasteText("测试C");
        SystemOperationUtil.pasteText("测试D");
        ExecutorService executor = VirtualThreadUtil.getExecutor();
        try {
            // 等待所有任务完成
            if (!executor.awaitTermination(300, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow(); // 超过时间限制则强制关闭
            }
        } catch (InterruptedException e) {
            executor.shutdownNow(); // 在等待期间被中断则强制关闭
        }
    }
}