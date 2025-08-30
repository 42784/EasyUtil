package dczx.axolotl.util;

import dczx.axolotl.entity.RobotKeyEvent;
import dczx.axolotl.thread.VirtualThreadUtil;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/8/30 11:40
 */
class RobotUtilTest {
    @Test
    @SneakyThrows
    void keyPressed() {
        if (!GraphicsEnvironment.isHeadless()) {
            //有显示器和键鼠
            RobotUtil.pressAndReleaseKeyEvent(new RobotKeyEvent(false, false, true, KeyEvent.VK_TAB));
            Thread.sleep(30);
            RobotUtil.pressAndReleaseKeyEvent(new RobotKeyEvent(false, false, true, KeyEvent.VK_TAB));
        }
        ExecutorService executor = VirtualThreadUtil.getExecutor();
        try {
            // 等待所有任务完成
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow(); // 超过时间限制则强制关闭
            }
        } catch (InterruptedException e) {
            executor.shutdownNow(); // 在等待期间被中断则强制关闭
        }
    }

}