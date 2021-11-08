package com.csw.mysqldate.controller;

public class ISNumberOrISCharacter {
    public static void main(String[] args) {
        String ss1 = "35879076543";
        String ss2 = "fsgjnbvgrethjnb";
        String ss3 = "5tfvgy7uhju89yugfde4f";
        System.out.println("isNumber/////////////////////////");
        System.out.println(isNumber(ss1));
        System.out.println(isNumber(ss2));
        System.out.println(isNumber(ss3));
        System.out.println("isCharacter//////////////////////////");
        System.out.println(isCharacter(ss1));
        System.out.println(isCharacter(ss2));
        System.out.println(isCharacter(ss3));

    }

    public static boolean isNumber(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!(str.charAt(i) >= '0' && str.charAt(i) <= '9')) {
                return false;
            }
        }
        return true;
    }

    public static boolean isCharacter(String str) {
        for (int i = 0; i < str.length(); i++) {
            if (!((str.charAt(i) >= 'a' && str.charAt(i) <= 'z') || (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z'))) {
                return false;
            }
        }
        return true;
    }
}
