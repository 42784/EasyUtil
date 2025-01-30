package dczx.axolotl.command;

import dczx.axolotl.util.ExecUtil;

import java.util.concurrent.Executors;

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

    public CommandResult runCommand(String command) {
        return runCommand(command, true);
    }

    public CommandResult runCommand(String command, boolean isBlock) {
        CommandResult result = new CommandResult();
        if (isBlock)
            ExecUtil.exec(command, path, true, result::out, result::err, result::setExitCode);
            else
            ExecUtil.exec(command, path, Executors.newCachedThreadPool(), result::out, result::err, result::setExitCode);
        return result;
    }

}
