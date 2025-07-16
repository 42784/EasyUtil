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
        ProcessTerminal terminal = new ProcessTerminal("cmd.exe",System.getProperty("user.dir"));


//        terminal.regRefreshListener((TerminalStringRefresh) (output, error) -> System.out.println("output = " + output));

        // 执行多个命令
//        terminal.execute("help");
//        terminal.execute("java -version");
//
        terminal.execute("dir");
        Thread.sleep(50);
        terminal.execute("cd ..");
        Thread.sleep(5);
        terminal.execute("dir");

        Thread.sleep(500);


        // 获取全部历史记录
        System.out.println("Full history:");
        terminal.getHistory().forEach(System.out::println);

    }
}