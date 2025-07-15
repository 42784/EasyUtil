package dczx.axolotl.command;

import lombok.Data;

import java.util.ArrayList;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/12/14 22:15
 */
@Data
@Deprecated
public class CommandResult {
    private ArrayList<String> out;
    private ArrayList<String> err;
    private Integer exitCode;

    public void out(String s) {
        out.add(s);
    }

    public void err(String s) {
        err.add(s);
    }

    public CommandResult() {
        out = new ArrayList<>();
        err = new ArrayList<>();
    }
}
