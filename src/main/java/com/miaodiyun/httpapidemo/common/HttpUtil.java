package com.miaodiyun.httpapidemo.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * http璇锋眰宸ュ叿
 */
public class HttpUtil {
    /**
     * 鏋勯�犻�氱敤鍙傛暟timestamp銆乻ig鍜宺espDataType
     * 
     * @return
     */
    public static String createCommonParam(String sid,String token) {
        // 鏃堕棿鎴�
        long timestamp = System.currentTimeMillis();
        // 绛惧悕
       String sig = DigestUtils.md5Hex(sid + token + timestamp);

        return "&timestamp=" + timestamp + "&sig=" + sig + "&respDataType=" + Config.RESP_DATA_TYPE;
    }

    /**
     * post璇锋眰
     * 
     * @param url
     *            鍔熻兘鍜屾搷浣�
     * @param body
     *            瑕乸ost鐨勬暟鎹�
     * @return
     * @throws IOException
     */
    public static String post(String url, String body) {
        System.out.println("body:" + System.lineSeparator() + body);

        String result = "";
        try {
            OutputStreamWriter out = null;
            BufferedReader in = null;
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();

            // 璁剧疆杩炴帴鍙傛暟
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(20000);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 鎻愪氦鏁版嵁
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(body);
            out.flush();

            // 璇诲彇杩斿洖鏁版嵁
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = "";
            boolean firstLine = true; // 璇荤涓�琛屼笉鍔犳崲琛岀
            while ((line = in.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                } else {
                    result += System.lineSeparator();
                }
                result += line;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 鍥炶皟娴嬭瘯宸ュ叿鏂规硶
     * 
     * @param url
     * @return
     */
    public static String postHuiDiao(String url, String body) {
        String result = "";
        try {
            OutputStreamWriter out = null;
            BufferedReader in = null;
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();

            // 璁剧疆杩炴帴鍙傛暟
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(20000);

            // 鎻愪氦鏁版嵁
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(body);
            out.flush();

            // 璇诲彇杩斿洖鏁版嵁
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
            String line = "";
            boolean firstLine = true; // 璇荤涓�琛屼笉鍔犳崲琛岀
            while ((line = in.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                } else {
                    result += System.lineSeparator();
                }
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}