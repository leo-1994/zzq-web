package com.leo.zzq.util;

import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author chao.li
 * @date 2019-02-26 15:13
 */
public class StringUtil {

    public static boolean isEmpty(String str) {
        return StringUtils.isEmpty(str);
    }

    public static String join(String[] arr, String delimiter) {
        StringBuilder sb = new StringBuilder();
        String pre = "";
        for (String s : arr) {
            sb.append(pre).append(s);
            pre = delimiter;
        }
        return sb.toString();
    }

    public static String join(List<String> list, String delimiter) {
        StringBuilder sb = new StringBuilder();
        String pre = "";
        for (String s : list) {
            sb.append(pre).append(s);
            pre = delimiter;
        }
        return sb.toString();
    }
}
