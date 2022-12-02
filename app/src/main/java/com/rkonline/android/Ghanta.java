package com.rkonline.android;

public class Ghanta {
    private String status,times;

    public Ghanta(String status, String times) {
        this.status = status;
        this.times = times;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }
}
