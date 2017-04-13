package com.sunfusheng.vlayout.demo.util;

import java.util.List;

/**
 * Created by sunfusheng on 2017/4/13.
 */
public class AppUtil {

    public static <T> boolean notEmpty(List<T> list) {
        return !isEmpty(list);
    }

    public static <T> boolean isEmpty(List<T> list) {
        if (list == null || list.size() == 0) {
            return true;
        }
        return false;
    }
}
