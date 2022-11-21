/**
 * {@code @Description}
 *
 * @author liyajun
 * {@code @create}          2022-11-21 13:26
 */

package com.group7.sanSongMall.util;

import java.io.IOException;
import java.util.Scanner;

public class CPUSerialNumber {
    /**
     * 获取CPU序列号
     * @return
     * @throws IOException
     */
    public static String getCPUSerialNumber() {
        String next;
        try {
            Process process = Runtime.getRuntime().exec(new String[]{"wmic", "cpu", "get", "ProcessorId"});
            process.getOutputStream().close();
            Scanner sc = new Scanner(process.getInputStream());
            String serial = sc.next();
            next = sc.next();
        } catch (IOException e) {
            throw new RuntimeException("获取CPU序列号失败");
        }
        return next;
    }
}
