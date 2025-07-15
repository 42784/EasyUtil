package dczx.axolotl.terminal;

import java.io.InputStream;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/15 23:48
 */
public interface TerminalStreamRefresh extends TerminalRefresh<InputStream> {
    @Override
    void refresh(InputStream outputStream, InputStream errorStream);
}
