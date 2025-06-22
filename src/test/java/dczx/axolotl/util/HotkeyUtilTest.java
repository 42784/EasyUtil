package dczx.axolotl.util;

import org.junit.jupiter.api.Test;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/6/22 13:09
 */
class HotkeyUtilTest {

    @Test
    void addHotkey() throws InterruptedException {
        // 添加热键 F4
        HotkeyUtil.addHotkey(1, KeyEvent.VK_F4, 0, () -> {
            System.out.println("F4 pressed");
        });

        // 添加热键 Ctrl + A
        HotkeyUtil.addHotkey(2, KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK, () -> {
            System.out.println("Ctrl + A pressed");
        });

        // 删除热键 ID=1
        HotkeyUtil.removeHotkey(1);

        // 更新热键 ID=2 为 Ctrl + B
        HotkeyUtil.updateHotkey(2, KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK, () -> {
            System.out.println("Ctrl + B pressed");
        });

    }
}