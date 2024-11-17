package dczx.axolotl.util;

import java.util.Scanner;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/11/16 22:02
 * 简易的Scanner工具
 */
public class ScannerUtil {
    public static String nextLine(boolean isCanBeNull) {
        if (isCanBeNull) {//输入可以为空
            return getScanner().nextLine();
        }
        String nextLine;
        while ((nextLine = getScanner().nextLine()).isEmpty()) {
            System.out.print("错误，请重新输入: ");
        }
        return nextLine;
    }

    public static String nextLine() {
        return nextLine(false);
    }

    public static int nextInt() {
        return getScanner().nextInt();
    }

    public static double nextDouble() {
        return getScanner().nextDouble();
    }

    public static char nextChar() {
        return nextLine().charAt(0);
    }

    /**
     * 输入一个字符，并将其转换为大写
     */
    public static char nextUpChar() {
        return nextLine().toUpperCase().charAt(0);
    }

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }

}
