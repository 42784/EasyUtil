package dczx.axolotl.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/9/16 22:46
 */
public class DateUtil {
    /**
     * 格式化日期
     *
     * @param date    日期对象
     * @param pattern 格式类型
     * @return 处理后的字符串
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     * 格式化日期
     *
     * @param date 日期对象
     * @return 处理后的字符串
     */
    public static String formatDate(Date date) {
        return formatDate(date, "yyyy-MM-dd hh:mm:ss");
    }
}
