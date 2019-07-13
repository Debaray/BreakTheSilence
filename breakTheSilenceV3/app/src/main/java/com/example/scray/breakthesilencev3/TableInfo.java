package com.example.scray.breakthesilencev3;

/**
 * Created by masru on 21-Nov-18.
 */

public class TableInfo {
    private String sentance;
    private int id,freq;

    public TableInfo()
    {

    }
    public TableInfo(int id, String sentence,int freq) {
        this.sentance = sentence;
        this.id = id;
        this.freq = freq;
    }
    public TableInfo(String sentence,int freq) {
        this.sentance = sentence;
        this.freq = freq;
    }


    public String getSentence() {
        return sentance;
    }

    public int getId() {
        return id;
    }

    public int getFreq() {
        return freq;
    }

    public void setSentence(String sentance) {
        this.sentance = sentance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }
}
