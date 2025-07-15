package dczx.axolotl.terminal;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/15 16:59
 */
public class ProcessTerminal extends SimpleTerminal {

    private static final Runtime runtime = Runtime.getRuntime();

    private final String workDirectory;

    public ProcessTerminal(String workDirectory) {
        this.workDirectory = workDirectory;
    }


    public ProcessTerminal(int maxHistorySize, String workDirectory) {
        super(maxHistorySize);
        this.workDirectory = workDirectory;
    }

    @Override
    protected CommandResult executeCommand(String command) throws IOException, InterruptedException {
        Process process = runtime.exec(command, null, new File(workDirectory));
        process.waitFor();
        return new CommandResult(
                process.getInputStream(),
                process.getErrorStream()
        );
    }

    private InputStream stringToInputStream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }
}