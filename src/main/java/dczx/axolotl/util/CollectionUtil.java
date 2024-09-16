package dczx.axolotl.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * @author AxolotlXM
 * @version 1.0
 * @since 2024/9/16 22:02
 * 集合操作工具
 */
public class CollectionUtil {
    /**
     * 从集合里面随机抽取一个元素
     *
     * @param collection 集合
     * @return 随机元素
     */
    public static <E> E getRandomElement(Collection<E> collection) {
        List<E> list = new ArrayList<>(collection);
        return getRandomElement(list);
    }

    /**
     * 从集合里面随机抽取一个元素
     *
     * @param list 集合
     * @return 随机元素
     */
    public static <E> E getRandomElement(List<E> list) {
        int nextInt = new Random().nextInt(list.size());
        return list.get(nextInt);
    }

    /**
     * 从集合里面随机抽取多个元素
     *
     * @param collection 集合
     * @return 随机元素
     */
    public static <E> List<E> getRandomElement(Collection<E> collection, int amount) {
        List<E> list = new ArrayList<>(collection);
        List<E> result = new ArrayList<>(amount);
        for (int i = 0; i < amount; i++) {
            result.add(getRandomElement(list));
        }
        return result;
    }

}
