package dczx.axolotl.command;

import dczx.axolotl.util.ExecUtil;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/12/14 22:15
 */
public class DefaultExecutor {
    private String path;

    public DefaultExecutor(String path) {
        this.path = path;
    }

    public  CommandResult runCommand(String command) {
        return runCommand(command, true);
    }

    public  CommandResult runCommand(String command, boolean isBlock) {
        CommandResult result = new CommandResult();
        ExecUtil.exec(command, path, isBlock, result::out, result::err, result::setExitCode);
        return result;
    }

}
