package dczx.axolotl.terminal;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/15 23:48
 */
public interface TerminalStringRefresh extends  TerminalRefresh<String>{
    void refresh(String output, String error);
}
