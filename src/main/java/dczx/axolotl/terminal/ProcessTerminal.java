package dczx.axolotl.terminal;

import java.io.*;
import java.nio.charset.StandardCharsets;
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
    private final String startCommand;
    private Process process;
    private BufferedWriter writer;

    private Thread outputThread;
    private Thread errorThread;
    private Thread inputThread;

    private final AtomicBoolean isRunning = new AtomicBoolean(true);

    public long getPid(){
        return process.pid();
    }

    public boolean isRunning() {
        return isRunning.get();
    }

    {
        clearRefreshListener();//自己维护历史记录
    }

    public ProcessTerminal(String startCommand, String workDirectory) {
        this.startCommand = startCommand;
        this.workDirectory = workDirectory;
    }

    public ProcessTerminal(int maxHistorySize, String startCommand, String workDirectory) {
        super(maxHistorySize);
        this.startCommand = startCommand;
        this.workDirectory = workDirectory;
    }


    private void readStream(InputStream stream, boolean isError) {
        List<TerminalStringRefresh> refreshListener = getRefreshListener(TerminalStringRefresh.class);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(stream, System.getProperty("sun.jnu.encoding")))) {
            String line;
            while ((line = reader.readLine()) != null && isRunning.get()) {
                if (isError) {
                    String finalLine = line;
                    refreshListener.forEach(refresh -> {
                        refresh.refresh(null, finalLine);
                    });
                    addHistory(HistoryEntry.Type.ERROR, line);
                } else {
                    String finalLine = line;
                    refreshListener.forEach(refresh -> {
                        refresh.refresh(finalLine, null);
                    });
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
        ProcessBuilder builder;
        builder = new ProcessBuilder(startCommand);
        builder.directory(new File(workDirectory));
        builder.redirectErrorStream(false); // 分开处理标准输出和错误输出

        process = builder.start();
        writer = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));

        // 读取标准输出流
        errorThread = new Thread(() -> readStream(process.getInputStream(), false));
        // 读取错误流
        outputThread = new Thread(() -> readStream(process.getErrorStream(), true));

        // 监听进程是否结束
        inputThread = new Thread(() -> {
            try {
                process.waitFor();
                isRunning.set(false);
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        outputThread.start();
        errorThread.start();
        inputThread.start();
    }

    @Override
    protected CommandResult executeCommand(String command) throws IOException, InterruptedException {
        if (process == null || !isRunning.get()) {
            startInteractiveShell();
        }

        writer.write(command);
        writer.newLine();
        writer.flush();

        return new CommandResult("", "");

    }

    @Override
    public void stop() {
        process.destroyForcibly();
        isRunning.set(false);
        outputThread.interrupt();
        errorThread.interrupt();
        inputThread.interrupt();
    }

    /**
     * 强制停止进程
     * 中断其Pid
     */
    public boolean stopForcibly() {
        process.destroyForcibly();
        isRunning.set(false);
        outputThread.interrupt();
        errorThread.interrupt();
        inputThread.interrupt();

        long pid = process.pid();
        return killProcess(pid);
    }
    private boolean killProcess(long pid) {
        try {
            String cmd;
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                cmd = "taskkill /F /T /PID " + pid;
            } else {
                cmd = "kill -9 " + pid;
            }
            Process process = Runtime.getRuntime().exec(cmd);
            return process.waitFor() == 0;
        } catch (Exception e) {
            return false;
        }
    }

}