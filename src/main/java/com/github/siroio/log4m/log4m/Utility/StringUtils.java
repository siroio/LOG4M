package com.github.siroio.log4m.log4m.Utility;

public class StringUtils {
    private StringUtils(){}

    public static String replaceByHand(final String original, final String old, final String replace) {
        int offset = 0;
        StringBuilder builder = new StringBuilder(original.length());
        int index;
        while (0 <= (index = original.indexOf(old, offset))) {
            builder.append(original, offset, index);
            builder.append(replace);
            offset = index + old.length();
        }
        builder.append(original, offset, original.length());
        return builder.toString();
    }
}
