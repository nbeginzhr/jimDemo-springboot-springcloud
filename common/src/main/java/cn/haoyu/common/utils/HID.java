package cn.haoyu.common.utils;

import java.util.Random;

/**
 * Created by haoyu on 2018/6/9.
 */
public class HID {

    private static final char[] ELEMENT = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
            'v', 'w', 'x', 'y', 'z'};

    private static Random random = new Random();

    public static String create() {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 12; i++) {
            buffer.append(ELEMENT[random.nextInt(62)]);
        }
        return buffer.toString();
    }


}
