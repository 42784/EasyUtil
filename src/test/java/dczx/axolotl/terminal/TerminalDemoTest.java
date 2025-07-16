package dczx.axolotl.terminal;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;

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


        terminal.regRefreshListener((TerminalStringRefresh) (output, error) -> System.out.println("output = " + output));
        terminal.regRefreshListener((TerminalStringRefresh) (output, error) -> System.out.println("error = " + error));

        // 执行多个命令
//        terminal.execute("help");
        terminal.execute("java -version");
//
        terminal.execute("java");
        Thread.sleep(500);
        System.out.println("terminal.isRunning() = " + terminal.isRunning());
        terminal.stop();//下面的已经不会执行了
        System.out.println("terminal.isRunning() = " + terminal.isRunning());
        Thread.sleep(100);
        terminal.execute("java -version");
        Thread.sleep(100);



        // 获取全部历史记录
        System.out.println("Full history:");
        terminal.getHistory().forEach(System.out::println);

    }
}