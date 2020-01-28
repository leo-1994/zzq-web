package com.leo.zzq.util;

import java.util.List;

/**
 * @author chao.li
 * @date 2019/1/22 13:51
 */
public class ListUtil {
    public static <T> int count(List<T> list, T item) {
        int count = 0;
        for (T t : list) {
            if (t.equals(item)) {
                count++;
            }
        }
        return count;
    }
}
