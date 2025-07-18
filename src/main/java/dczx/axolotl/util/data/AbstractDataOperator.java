package dczx.axolotl.util.data;

import lombok.SneakyThrows;
/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2025/7/18 10:44
 */
public abstract class AbstractDataOperator {

    public AbstractDataOperator() {
    }

    /**
     * 初始化调用
     * 由子类决定调用位置
     */
    public void init() {
        load();
    }

    public abstract void load();

    public abstract void reload();

    public abstract void reset();

    public abstract void save();
}
