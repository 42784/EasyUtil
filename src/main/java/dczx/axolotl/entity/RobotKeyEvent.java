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
    private int keyCode;

    public RobotKeyEvent(int keyCode) {
        this.withShift = false;
        this.withCtrl = false;
        this.withAlt = false;
        this.keyCode = keyCode;
    }
}
