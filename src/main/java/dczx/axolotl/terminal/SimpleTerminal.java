package dczx.axolotl.terminal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/15 16:59
 */


public abstract class SimpleTerminal {
    // 历史记录存储
    private final List<HistoryEntry> history = new ArrayList<>();
    // 刷新器
    private final List<TerminalRefresh> refreshListener = new ArrayList<>();
    // 最大历史记录条数
    private final int maxHistorySize;

    {
        refreshListener.add((TerminalStreamRefresh) (outputStream, errorStream) -> {
            try {
                String output = readStreamSafely(outputStream);
                String error = readStreamSafely(errorStream);

                if (!output.isEmpty()){
                    addHistory(HistoryEntry.Type.OUTPUT, output);
                }
                if (!error.isEmpty()) {
                    addHistory(HistoryEntry.Type.ERROR, error);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        refreshListener.add((TerminalStringRefresh) (output, error) -> {
            addHistory(HistoryEntry.Type.OUTPUT, output);
            addHistory(HistoryEntry.Type.ERROR, error);
        });
    }

    public static String readStream(InputStream stream) {
        if (stream == null) {
            return "";
        }

        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream()) {
            byte[] data = new byte[1024];
            int bytesRead;
            while ((bytesRead = stream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, bytesRead);
            }
            return buffer.toString("UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private String readStreamSafely(InputStream stream) {
        try {
            if (stream == null) return "";

            // 先检查是否有数据
            if (stream.available() <= 0) return "";

            // 读取数据
            return readStream(stream);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            try {
                if (stream != null) stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public SimpleTerminal() {
        this(100); // 默认保留100条历史记录
    }

    public SimpleTerminal(int maxHistorySize) {
        this.maxHistorySize = maxHistorySize;
    }

    public void regRefreshListener(TerminalRefresh listener) {
        refreshListener.add(listener);
    }

    public void unRegRefreshListener(TerminalRefresh listener) {
        refreshListener.remove(listener);
    }

    public void clearRefreshListener() {
        refreshListener.clear();
    }
    public <T extends TerminalRefresh> List<T > getRefreshListener(Class<T > type) {
        List<T> result = new ArrayList<>();
        for (TerminalRefresh listener : refreshListener) {
            if (type.isInstance(listener)) {
                result.add(type.cast(listener));
            }
        }
        return result;
    }

    /**
     * 执行命令
     *
     * @param command 要执行的命令字符串
     */
    public void execute(String command) throws Exception {
        // 记录输入历史
        addHistory(HistoryEntry.Type.INPUT, command);

        // 执行命令
        CommandResult result = executeCommand(command);

        refreshListener
                .stream()
                .filter(refreshListener -> refreshListener instanceof TerminalStringRefresh)
                .forEach(refreshListener -> refreshListener.refresh(result.output, result.error));

        // 刷新输出
        if (result.outputStream != null && result.errorStream != null) {
            refreshListener
                    .stream()
                    .filter(refreshListener -> refreshListener instanceof TerminalStreamRefresh)
                    .forEach(refreshListener -> refreshListener.refresh(result.outputStream, result.errorStream));
        }
    }

    /**
     * 添加历史记录
     */
    void addHistory(HistoryEntry.Type type, String content) {
        if (history.size() >= maxHistorySize) {
            history.remove(0); // 移除最旧的记录
        }
        history.add(new HistoryEntry(type, content));
    }

    /**
     * 抽象方法 - 子类必须实现的命令执行逻辑
     */
    protected abstract CommandResult executeCommand(String command) throws IOException, InterruptedException;


    /**
     * 获取全部历史记录
     */
    public List<HistoryEntry> getHistory() {
        return new ArrayList<>(history);
    }

    /**
     * 获取特定类型的历史记录
     */
    public List<String> getHistoryByType(HistoryEntry.Type type) {
        List<String> result = new ArrayList<>();
        for (HistoryEntry entry : history) {
            if (entry.type == type) {
                result.add(entry.content);
            }
        }
        return result;
    }

    /**
     * 清空历史记录
     */
    public void clearHistory() {
        history.clear();
    }

    /**
     * 历史记录条目
     */
    public static class HistoryEntry {
        public enum Type {INPUT, OUTPUT, ERROR}

        public final Type type;
        public final String content;
        public final long timestamp;

        public HistoryEntry(Type type, String content) {
            this.type = type;
            this.content = content;
            this.timestamp = System.currentTimeMillis();
        }

        @Override
        public String toString() {
            return String.format("[%s][%tT] %s", type, timestamp, content);
        }
    }

    /**
     * 命令结果封装类
     */
    public static class CommandResult {
        public String output;
        public String error;
        public InputStream outputStream;
        public InputStream errorStream;

        public CommandResult(String output, String error,
                             InputStream outputStream, InputStream errorStream) {
            this.output = output;
            this.error = error;
            this.outputStream = outputStream;
            this.errorStream = errorStream;
        }

        public CommandResult(InputStream outputStream, InputStream errorStream) {
            this.outputStream = outputStream;
            this.errorStream = errorStream;
        }

        public CommandResult(String output, String error) {
            this.output = output;
            this.error = error;
        }
    }
}