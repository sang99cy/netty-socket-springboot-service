package com.quangsang.springbaseexample;

public class TestLeetCode {


    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
            }
        }
        int x = 0;
        return prefix;
    }

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        String kq = longestCommonPrefix(strs);
        System.out.println(kq);
        /*String s1 = "this is index of example";

        //Truyền vào chuỗi con
        int index1 = s1.indexOf("is");
        int index2 = s1.indexOf("index");
        System.out.println(index1 + "  " + index2);//2 8

        //Truyền vào chuỗi con và chỉ số bắt đầu
        int index3 = s1.indexOf("is", 4);
        System.out.println(index3);//5

        //Truyền vào giá trị Char
        int index4 = s1.indexOf('s');
        System.out.println(index4);//3

        //Truyền vào giá trị Char
        int index5 = s1.indexOf('k'); *//* khong tim duoc pha tu thi reutrn -1*//*
        System.out.println(index5);//3*/
    }
}
