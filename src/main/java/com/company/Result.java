package com.company;

import java.util.ArrayList;

public class Result {
    public ArrayList<String> disappearedURL = new ArrayList<>();
    public ArrayList<String> changedURL = new ArrayList<>();
    public ArrayList<String> newURL = new ArrayList<>();

    public Result() {    }

    public Result(ArrayList<String> disappearedURL, ArrayList<String> changedURL, ArrayList<String> newURL) {
        this.disappearedURL = disappearedURL;
        this.changedURL = changedURL;
        this.newURL = newURL;
    }
}
