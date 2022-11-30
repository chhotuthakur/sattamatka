package com.rkonline.android;

public class GhantaModel {
    private String status,times;

    public GhantaModel(String status, String times) {
        this.status = status;
        this.times = times;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return times;
    }

    public void setTime(String times) {
        this.times = times;
    }
}
