package com.spring.restfinder.rest_finder.dto.restaurantGoogle;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Period {
    private TimeSlot open;
    private TimeSlot close;

    // Getters e setters


    public TimeSlot getOpen() {
        return this.open;
    }

    public void setOpen(TimeSlot open) {
        this.open = open;
    }

    public TimeSlot getClose() {
        return this.close;
    }

    public void setClose(TimeSlot close) {
        this.close = close;
    }

}