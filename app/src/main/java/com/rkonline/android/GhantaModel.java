package com.rkonline.android;

public class GhantaModel {
    private String result,rtime,rdate;

    public GhantaModel(String result, String rtime, String rdate) {
        this.result = result;
        this.rtime = rtime;
        this.rdate = rdate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getRtime() {
        return rtime;
    }

    public void setRtime(String rtime) {
        this.rtime = rtime;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }
}
