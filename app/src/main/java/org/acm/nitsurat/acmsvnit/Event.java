package org.acm.nitsurat.acmsvnit;

/**
 * Created by karan1106 on 15/12/16.
 */

public class Event {
    private String mTitle;
    private String mDate;
    private String mTimings;
    public Event(String title, String date, String time){
        mTitle = title;
        mDate = date;
        mTimings = time;
    }
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getTimings() {
        return mTimings;
    }

    public void setTimings(String timings) {
        mTimings = timings;
    }
}
