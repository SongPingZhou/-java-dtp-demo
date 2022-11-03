package com.spz;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 宋平州
 */
public  class DtpUtil {
    public static Map<String, DtpExecutor> map = new HashMap<>();

    public static void set(String name, DtpExecutor dtpExecutor) {
        map.put(name, dtpExecutor);
    }

    public static DtpExecutor get(String name) {
        return map.get(name);
    }
}
