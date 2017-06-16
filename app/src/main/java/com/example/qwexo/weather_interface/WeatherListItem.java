package com.example.qwexo.weather_interface;

import android.graphics.drawable.Drawable;

/**
 * Created by qwexo on 2017-06-13.
 */
////////////사용자정의 아이템
public class WeatherListItem {
    private String dateText;
    private String amTempText;
    private Drawable amSkyImage;
    private String amSkyText;
    private String pmTempText;
    private Drawable pmSkyImage;
    private String pmSkyText;

    public void setAmSkyImage(Drawable amSkyImage) {
        this.amSkyImage = amSkyImage;
    }

    public void setAmSkyText(String amSkyText) {
        this.amSkyText = amSkyText;
    }

    public void setAmTempText(String amTempText) {
        this.amTempText = amTempText;
    }

    public void setDateText(String dateText) {
        this.dateText = dateText;
    }

    public void setPmSkyImage(Drawable pmSkyImage) {
        this.pmSkyImage = pmSkyImage;
    }

    public void setPmSkyText(String pmSkyText) {
        this.pmSkyText = pmSkyText;
    }

    public void setPmTempText(String pmTempText) {
        this.pmTempText = pmTempText;
    }

    public Drawable getAmSkyImage() {
        return amSkyImage;
    }

    public Drawable getPmSkyImage() {
        return pmSkyImage;
    }

    public String getAmSkyText() {
        return amSkyText;
    }

    public String getAmTempText() {
        return amTempText;
    }

    public String getDateText() {
        return dateText;
    }

    public String getPmSkyText() {
        return pmSkyText;
    }

    public String getPmTempText() {
        return pmTempText;
    }
}
