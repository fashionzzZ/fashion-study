package com.fashion.jvm;

public class Test {

    public static void main(String[] args) {
        String str = "\"Content-Type: application/json\"";
        System.out.println(str);
        String newStr = str.replaceAll("\"", "\\\\\"");
        System.out.println(newStr);
    }
}
