package dczx.axolotl.entity;

import dczx.axolotl.interfaces.Authentication;
import lombok.AllArgsConstructor;
import lombok.Data;


/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/9/30 20:19
 * <p>
 * 通过时间来简易认证 可用于检验请求是否符合要求  并且判断时间是否在限度内
 */
@Data
@AllArgsConstructor
public class TimeAuthentication implements Authentication {
    private String password;


    @Override
    public String generateToken() {
        return String.format(password+"%d" + password, System.currentTimeMillis());
    }

    @Override
    public boolean verifyToken(String token) {
        if (token.contains(password)){
            String[] split = token.split(password);
            if (split.length == 2){
                long time = Long.parseLong(split[1]);
                long now = System.currentTimeMillis();
                return  Math.abs(now-time)<=3000;
            }
        }
        return false;
    }
}
