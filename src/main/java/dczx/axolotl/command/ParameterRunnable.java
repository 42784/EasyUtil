package dczx.axolotl.command;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/9/16 22:19
 */
@FunctionalInterface
public interface ParameterRunnable<E> {
    void run(E e);
}
