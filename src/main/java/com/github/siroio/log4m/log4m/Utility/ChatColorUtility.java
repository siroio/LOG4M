package com.github.siroio.log4m.log4m.Utility;

public class ChatColorUtility {
    private ChatColorUtility(){}

    public static boolean isColorCode(String hexColor) {
        return hexColor.matches("^#[0-9a-fA-F]{6}$");
    }

    public static String validateColorCode(String str) {
        String pattern = "^#([0-9a-fA-F]{3}|[0-9a-fA-F]{6})$";
        if (str.matches(pattern)) {
            char r = str.charAt(1);
            char g = str.charAt(2);
            char b = str.charAt(3);
            return str.length() == 4 ? "#" + r + r + g + g + b + b : str;
        }
        return null;
    }
}
