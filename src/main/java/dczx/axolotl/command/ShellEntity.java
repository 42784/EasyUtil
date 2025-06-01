package dczx.axolotl.command;

import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/6/1 20:49
 */
@Data
public class ShellEntity {
    private final Process process;
    private final InputStream inputStream;
    private final OutputStream outputStream;
    private final InputStream errorStream;

    public ShellEntity(String command, String runPath) throws IOException {
        process= ExecUtil.exec(command, runPath);
        inputStream = process.getInputStream();
        outputStream = process.getOutputStream();
        errorStream = process.getErrorStream();
    }

    public void input(String input) throws IOException {
        outputStream.write(input.getBytes());
        outputStream.flush();
    }

    public void inputWithLn(String input) throws IOException {
        outputStream.write(input.getBytes());
        outputStream.write(System.lineSeparator().getBytes());
        outputStream.flush();
    }
}
