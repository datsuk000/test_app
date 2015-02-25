package com.example.roman.test_app.data;

public class Header {
    private String mHeaderId;
    private long mUpdate;
    private long mDate;
    private int mRanking;
    private String mTitle;
    private String mSubTitle;
    private String mLink;

    public Header(String headerId, long update, long date, int ranking,
                  String title, String subTitle, String link) {
        mHeaderId = headerId;
        mUpdate = update;
        mDate = date;
        mRanking = ranking;
        mTitle = title;
        mSubTitle = subTitle;
        mLink = link;
    }

    public String getHeaderId() {
        return mHeaderId;
    }

    public long getUpdate() {
        return mUpdate;
    }

    public long getDate() {
        return mDate;
    }

    public int getRanking() {
        return mRanking;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getSubTitle() {
        return mSubTitle;
    }

    public String getLink() {
        return mLink;
    }

}
