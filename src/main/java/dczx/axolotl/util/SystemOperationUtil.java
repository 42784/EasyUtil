package dczx.axolotl.util;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;

import static java.lang.Thread.sleep;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/9/16 22:50
 * 有关系统的一些操作
 */
public class SystemOperationUtil {
    /**
     * 暂时修改粘贴板的数据
     *
     * @param text     修改内容
     * @param duration 持续时间
     */
    public static void setClipboardInTime(String text, long duration) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Transferable defaultClipboard = clipboard.getContents(null);//原始剪贴板数据
        setClipboard(text);//新数据
        new Thread(() -> {
            try {
                sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setClipboard(defaultClipboard);//还原剪贴板数据
        }).start();
    }

    /**
     * 暂时修改粘贴板的数据
     */
    public static void setClipboardInTime(String text) {
        setClipboardInTime(text, 3500);
    }


    /**
     * 修改粘贴板的数据
     */
    public static void setClipboard(String text) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(new StringSelection(text), null);//还原剪贴板数据
    }

    /**
     * 修改粘贴板的数据
     */
    public static void setClipboard(Transferable transferable) {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(transferable, null);//还原剪贴板数据
    }
}
