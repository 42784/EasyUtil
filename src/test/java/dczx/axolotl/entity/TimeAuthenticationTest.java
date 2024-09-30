package dczx.axolotl.entity;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/9/30 20:29
 */
class TimeAuthenticationTest {
    private final TimeAuthentication authentication = new TimeAuthentication("APasswordForThis");

    @Test
    void generateToken() {
        System.out.println("authentication.generateToken() = " + authentication.generateToken());
    }

    @Test
    @SneakyThrows
    void verifyToken() {
        String token = authentication.generateToken();
        Thread.sleep(2);
        assertTrue(authentication.verifyToken(token));
    }
}