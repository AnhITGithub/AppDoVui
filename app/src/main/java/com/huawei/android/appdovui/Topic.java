package com.huawei.android.appdovui;

public class Topic {
    private String tenchude;
    private int hinhanh;

    public String getTenchude() {
        return tenchude;
    }

    public void setTenchude(String tenchude) {
        this.tenchude = tenchude;
    }

    public int getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(int hinhanh) {
        this.hinhanh = hinhanh;
    }

    public Topic(String tenchude, int hinhanh) {
        this.tenchude = tenchude;
        this.hinhanh = hinhanh;
    }
}


