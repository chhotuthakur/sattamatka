package com.rkonline.android;

public class GhantaModel {
    public String times,statuss,results;

    public GhantaModel(String times, String statuss, String results) {
        this.times = times;
        this.statuss = statuss;
        this.results = results;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getStatuss() {
        return statuss;
    }

    public void setStatuss(String statuss) {
        this.statuss = statuss;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }
}
