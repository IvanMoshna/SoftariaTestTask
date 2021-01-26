package com.company;

import java.util.ArrayList;

public class Result {
    public ArrayList<String> disappearedURL;
    public ArrayList<String> changedURL;
    public ArrayList<String> newURL;

    public Result() {
        disappearedURL = new ArrayList<>();
        changedURL = new ArrayList<>();
        newURL = new ArrayList<>();
    }

    public Result(ArrayList<String> disappearedURL, ArrayList<String> changedURL, ArrayList<String> newURL) {
        this.disappearedURL = disappearedURL;
        this.changedURL = changedURL;
        this.newURL = newURL;
    }
}
