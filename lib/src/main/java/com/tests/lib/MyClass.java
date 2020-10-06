package com.tests.lib;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MyClass {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "ahmed");
        map.put(2, "karim");
        map.put(3, "hassan");
        map.put(4, "mahmoud");
        System.out.println(map.get(3));
        Set<String> set = new HashSet<>();
        set.add("hello");
        set.add("world");
        if (set.contains("hello"))
            System.out.println("hello");
    }
}