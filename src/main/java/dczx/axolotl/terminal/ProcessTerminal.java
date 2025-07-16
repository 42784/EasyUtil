package dczx.axolotl.terminal;

import java.io.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/15 16:59
 */
public class ProcessTerminal extends SimpleTerminal {

    private static final Runtime runtime = Runtime.getRuntime();

    private final String workDirectory;
    private Process process;
    private CommandResult commandResult;
    private OutputStream outputStream;
    private BufferedWriter writer;
    private final AtomicBoolean isRunning = new AtomicBoolean(true);

    {
        clearRefreshListener();//自己维护历史记录
    }

    public ProcessTerminal(String workDirectory) {
        this.workDirectory = workDirectory;
    }


    public ProcessTerminal(int maxHistorySize, String workDirectory) {
        super(maxHistorySize);
        this.workDirectory = workDirectory;
    }

    private void readStream(InputStream stream, boolean isError) {
        List<TerminalStringRefresh> refreshListener = getRefreshListener(TerminalStringRefresh.class);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream))) {
            String line;
            while ((line = reader.readLine()) != null && isRunning.get()) {
                if (isError) {
                    String finalLine = line;
                    refreshListener.forEach(refresh -> {refresh.refresh(null,finalLine);});
                    addHistory(HistoryEntry.Type.ERROR, line);
                } else {
                    String finalLine = line;
                    refreshListener.forEach(refresh -> {refresh.refresh(finalLine,null);});
                    addHistory(HistoryEntry.Type.OUTPUT, line);
                }

            }
        } catch (IOException e) {
            if (isRunning.get()) {
                e.printStackTrace();
            }
        }
    }

    private void startInteractiveShell() throws IOException {
        String os = System.getProperty("os.name").toLowerCase();
        ProcessBuilder builder;

        if (os.contains("win")) {
            builder = new ProcessBuilder("cmd.exe");
        } else {
            builder = new ProcessBuilder("/bin/bash");
        }

        builder.directory(new File(workDirectory));
        builder.redirectErrorStream(false); // 分开处理标准输出和错误输出

        process = builder.start();

        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

        // 读取标准输出流
        new Thread(() -> readStream(process.getInputStream(), false)).start();
        // 读取错误流
        new Thread(() -> readStream(process.getErrorStream(), true)).start();

        // 监听进程是否结束
        new Thread(() -> {
            try {
                process.waitFor();
                isRunning.set(false);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    protected CommandResult executeCommand(String command) throws IOException, InterruptedException {
        if (process == null || !isRunning.get()) {
            startInteractiveShell();
        }

        writer.write(command);
        writer.newLine();
        writer.flush();

        addHistory(HistoryEntry.Type.INPUT, command);

        return new CommandResult("", "");

    }

}