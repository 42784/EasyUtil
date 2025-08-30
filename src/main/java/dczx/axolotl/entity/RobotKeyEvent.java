package dczx.axolotl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/8/30 11:30
 */
@Data
@AllArgsConstructor
public class RobotKeyEvent {
    private boolean withShift;
    private boolean withCtrl;
    private boolean withAlt;
    private boolean withWin;
    private int keyCode;

    public RobotKeyEvent(int keyCode) {
        this.withShift = false;
        this.withCtrl = false;
        this.withAlt = false;
        this.keyCode = keyCode;
    }

    public static RobotKeyEvent of(int keyCode) {
        return new RobotKeyEvent(keyCode);
    }

    public static RobotKeyEvent withShift(int keyCode) {
        return new RobotKeyEvent(true, false, false, false, keyCode);
    }

    public static RobotKeyEvent withCtrl(int keyCode) {
        return new RobotKeyEvent(false, true, false, false, keyCode);
    }

    public static RobotKeyEvent withAlt(int keyCode) {
        return new RobotKeyEvent(false, false, true, false, keyCode);
    }
    public static RobotKeyEvent withWin(int keyCode) {
        return new RobotKeyEvent(false, false, false, true, keyCode);
    }
}
