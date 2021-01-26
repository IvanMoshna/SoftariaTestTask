package com.company;

import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        HashMap<String, String> todayMap = new HashMap<>();
        HashMap<String, String> yesterdayMap = new HashMap<>();



        ArrayList<String> urlList = new ArrayList<>();
        urlList.add("http://www.google.com");
        urlList.add("https://yandex.ru");
        urlList.add("https://vk.com");
        urlList.add("https://hh.ru");

        ArrayList<String> possibleURLList = new ArrayList<>();
        possibleURLList.add("https://github.com/");
        possibleURLList.add("https://www.youtube.com/");
        possibleURLList.add("https://spring.io/");

        fillMapWithHtmlCode(urlList, yesterdayMap);
        fillTodayMap(todayMap, yesterdayMap, possibleURLList);
        Result result = getChanges(todayMap, yesterdayMap);

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
        }
    }

    //заполняем вторую мапу
    public static void fillTodayMap(HashMap<String, String> todayMap, HashMap<String, String> yesterdayMap,
                                    ArrayList<String> possibleURLList) {

        for (String key : yesterdayMap.keySet()) {
            boolean isDisappearURL = new Random().nextBoolean();
            boolean isChangedHTML = new Random().nextBoolean();
            if (!isDisappearURL) {
                if (isChangedHTML) {
                    String content = yesterdayMap.get(key) + "CHANGED";
                    todayMap.put(key, content);
                } else {
                    todayMap.put(key, yesterdayMap.get(key));
                }
            }
        }

        ArrayList<String> newURLList = new ArrayList<>();
        for (String url: possibleURLList) {
            boolean isAddURL = new Random().nextBoolean();
            if(isAddURL) {
                newURLList.add(url);
            }
        }
        fillMapWithHtmlCode(newURLList, todayMap);

    }

    //сравниваем мапы
    public static Result getChanges(HashMap<String, String> todayMap,
                                                      HashMap<String, String> yesterdayMap) {
        Result result = new Result();

        for (String key : yesterdayMap.keySet()) {
            if(!todayMap.containsKey(key)) {
                result.disappearedURL.add(key);
            } else {
                //сравниваем значения
                boolean isSame = yesterdayMap.get(key).equals(todayMap.get(key));
                if(!isSame) {
                    result.changedURL.add(key);
                }
            }
        }
        for (String key : todayMap.keySet()){
            if(!yesterdayMap.containsKey(key)) {
                result.newURL.add(key);
            }
        }

        return result;
    }
}
