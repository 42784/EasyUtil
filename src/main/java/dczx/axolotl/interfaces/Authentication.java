package dczx.axolotl.interfaces;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/9/30 20:15
 */
public interface Authentication {
    Object generateToken();

    boolean verifyToken(String token);
}
