package dczx.axolotl.util;

import com.melloware.jintellitype.HotkeyListener;
import com.melloware.jintellitype.JIntellitype;

import java.awt.event.KeyEvent;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;


/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/6/22 13:06
 */

public class HotkeyUtil  {

    // 使用 ConcurrentHashMap 保证线程安全
    private static final Map<Integer, HotkeyInfo> registeredHotkeys = new ConcurrentHashMap<>();
    private static boolean initialized = false;

    // 热键监听器，只注册一次
    private static final HotkeyListener hotKeyListener = code -> {
        HotkeyInfo info = registeredHotkeys.get(code);
        if (info == null) return;

        long currentTime = System.currentTimeMillis();
        if (currentTime - info.lastTriggerTime >= 200) {
            info.lastTriggerTime = currentTime;
            new Thread(info.runnable).start();
        }
    };

    // 热键信息类
    private static class HotkeyInfo {
        final int keyCode;
        final int modifiers;
        final Runnable runnable;
        volatile long lastTriggerTime = 0L;

        HotkeyInfo(int keyCode, int modifiers, Runnable runnable) {
            this.keyCode = keyCode;
            this.modifiers = modifiers;
            this.runnable = runnable;
        }
    }

    // 初始化监听器（线程安全）
    private static void initializeIfNeeded() {
        if (!initialized) {
            synchronized (HotkeyUtil.class) {
                if (!initialized) {
                    JIntellitype.getInstance().addHotKeyListener(hotKeyListener);
                    initialized = true;
                }
            }
        }
    }

    // 添加热键
    public static void addHotkey(int id, int keyCode, int modifiers, Runnable runnable) {
        if (runnable == null) {
            throw new IllegalArgumentException("Runnable cannot be null");
        }

        JIntellitype.getInstance().registerHotKey(id, modifiers, keyCode);
        initializeIfNeeded();
        registeredHotkeys.put(id, new HotkeyInfo(keyCode, modifiers, runnable));
    }

    // 删除热键
    public static void removeHotkey(int id) {
        JIntellitype.getInstance().unregisterHotKey(id);
        registeredHotkeys.remove(id);
    }

    // 更新热键（替换 keyCode、modifiers、runnable）
    public static void updateHotkey(int id, int newKeyCode, int newModifiers, Runnable newRunnable) {
        removeHotkey(id);
        addHotkey(id, newKeyCode, newModifiers, newRunnable);
    }

    // 查询热键是否存在
    public static boolean containsHotkey(int id) {
        return registeredHotkeys.containsKey(id);
    }

    // 获取热键信息
    public static HotkeyInfo getHotkeyInfo(int id) {
        return registeredHotkeys.get(id);
    }

    // 清理所有热键（可选，用于程序关闭时）
    public static void shutdown() {
        for (Integer id : registeredHotkeys.keySet()) {
            JIntellitype.getInstance().unregisterHotKey(id);
        }
        registeredHotkeys.clear();
    }
}
