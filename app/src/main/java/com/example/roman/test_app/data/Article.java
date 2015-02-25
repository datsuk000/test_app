package com.example.roman.test_app.data;

public class Article {
    private String mArticleId;
    private String mAuthor;
    private String mCategoryId;
    private String mContent;
    private long mDate;

    public Article(String articleId, String author, String categoryId, String content, long date) {
        mArticleId = articleId;
        mAuthor = author;
        mCategoryId = categoryId;
        mContent = content;
        mDate = date;
    }

    public String getArticleId() {
        return mArticleId;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public String getCategoryId() {
        return mCategoryId;
    }

    public String getContent() {
        return mContent;
    }

    public long getDate() {
        return mDate;
    }

}
