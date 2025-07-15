package dczx.axolotl.terminal;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/16 0:29
 */
 class TerminalDemoTest {
    @SneakyThrows
    @Test
    public void RunTest(){
        // 创建终端实例，保留最近5条历史记录
        ProcessTerminal terminal = new ProcessTerminal(System.getProperty("user.dir"));
        // 执行多个命令
        terminal.execute("cmd /c");
        terminal.execute("help");
        terminal.execute("java -version");

        // 获取全部历史记录
        System.out.println("Full history:");
        terminal.getHistory().forEach(System.out::println);

    }
}