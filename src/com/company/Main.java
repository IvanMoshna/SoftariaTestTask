package com.company;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        HashMap<String, String> todayMap = new HashMap<>();
        HashMap<String, String> yesterdayMap = new HashMap<>();

        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("http://www.google.com");
        urlList.add("https://yandex.ru");
        urlList.add("https://vk.com");
        urlList.add("https://hh.ru");

        fillMapWithHtmlCode(urlList, yesterdayMap);
    }

    //заполняем мапу из списка URLов
    public static void fillMapWithHtmlCode(ArrayList<String> urlList, HashMap<String, String> map) {
        for (String url: urlList) {
            String content = null;
            URLConnection connection = null;
            try {
                connection =  new URL(url).openConnection();
                Scanner scanner = new Scanner(connection.getInputStream());
                scanner.useDelimiter("\\Z");
                content = scanner.next();
                scanner.close();
            } catch ( Exception ex ) {
                ex.printStackTrace();
            }
            map.put(url, content);
            //System.out.println(content);
        }
    }
}
