package com.example.kaow.caltest;

public class ActivityList {
    private int m_flagImageId;
    private String m_countryName;
    
    public ActivityList(int flagImageId, String countryName) {
        m_flagImageId = flagImageId;
        m_countryName = countryName;
    }

    public int getFlagImageId() {
        return m_flagImageId;
    }

    public void setFlagImageId(int flagImageId) {
        m_flagImageId = flagImageId;
    }

    public String getCountryName() {
        return m_countryName;
    }

    public void setCountryName(String countryName) {
        m_countryName = countryName;
    }
}
