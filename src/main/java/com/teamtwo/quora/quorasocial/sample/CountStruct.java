package com.teamtwo.quora.quorasocial.sample;

public class CountStruct {

    private int count;
    private String reactionType;

    public CountStruct() {
    }

    public CountStruct(int count, String reactionType) {
        this.count = count;
        this.reactionType = reactionType;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getReactionType() {
        return reactionType;
    }

    public void setReactionType(String reactionType) {
        this.reactionType = reactionType;
    }
}
