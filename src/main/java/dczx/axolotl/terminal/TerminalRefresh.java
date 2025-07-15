package dczx.axolotl.terminal;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/15 23:49
 */
public interface TerminalRefresh<T> {
     void refresh(T output, T error);
}
