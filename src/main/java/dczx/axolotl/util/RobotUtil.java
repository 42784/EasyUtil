package dczx.axolotl.util;

import dczx.axolotl.entity.RobotKeyEvent;
import dczx.axolotl.thread.ThreadUtil;
import dczx.axolotl.thread.VirtualThreadUtil;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/8/30 11:07
 * <p>
 * 模拟按键
 */
public class RobotUtil {
    private static final Robot robot;

    static {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            throw new RuntimeException(e);
        }
    }

    public static void pressKeyEvent(RobotKeyEvent event) {
        if (event.isWithCtrl()) robot.keyPress(KeyEvent.VK_CONTROL);
        if (event.isWithShift()) robot.keyPress(KeyEvent.VK_SHIFT);
        if (event.isWithAlt()) robot.keyPress(KeyEvent.VK_ALT);
        robot.keyPress(event.getKeyCode());
    }

    public static void releaseKeyEvent(RobotKeyEvent event) {
        if (event.isWithCtrl()) robot.keyRelease(KeyEvent.VK_CONTROL);
        if (event.isWithShift()) robot.keyRelease(KeyEvent.VK_SHIFT);
        if (event.isWithAlt()) robot.keyRelease(KeyEvent.VK_ALT);
        robot.keyRelease(event.getKeyCode());
    }
    public static void pressKey(int keycode) {
        robot.keyPress(keycode);
    }

    public static void releaseKey(int keycode) {
        robot.keyRelease(keycode);
    }

    public static void pressAndReleaseKeyEvent(RobotKeyEvent event, int delay) {
        run(() -> {
            pressKeyEvent(event);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            releaseKeyEvent(event);
        });
    }

    public static void pressAndReleaseKeyEvent(RobotKeyEvent event) {
        pressKeyEvent(event);
        releaseKeyEvent(event);
    }

    public static void pressAndReleaseKey(int keycode) {
        pressKey(keycode);
        releaseKey(keycode);
    }
    public static void pressAndReleaseKey(int keycode, int delay) {
        run(() -> {
            pressKey(keycode);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            releaseKey(keycode);
        });
    }

    private static void run(Runnable runnable) {
        VirtualThreadUtil.submit(runnable);
    }
}
