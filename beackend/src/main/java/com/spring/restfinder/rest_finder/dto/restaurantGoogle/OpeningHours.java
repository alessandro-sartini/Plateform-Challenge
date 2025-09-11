package com.spring.restfinder.rest_finder.dto.restaurantGoogle;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpeningHours {
    @JsonProperty("open_now")
    private boolean openNow;
    private List<Period> periods;
    @JsonProperty("weekday_text")
    private List<String> weekdayText;

    // Getters e setters

    public boolean isOpenNow() {
        return this.openNow;
    }

    public boolean getOpenNow() {
        return this.openNow;
    }

    public void setOpenNow(boolean openNow) {
        this.openNow = openNow;
    }

    public List<Period> getPeriods() {
        return this.periods;
    }

    public void setPeriods(List<Period> periods) {
        this.periods = periods;
    }

    public List<String> getWeekdayText() {
        return this.weekdayText;
    }

    public void setWeekdayText(List<String> weekdayText) {
        this.weekdayText = weekdayText;
    }

}