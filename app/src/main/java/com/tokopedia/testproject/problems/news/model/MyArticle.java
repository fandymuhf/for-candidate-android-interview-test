package com.tokopedia.testproject.problems.news.model;

public class MyArticle {
    public static final String TABLE_NAME = "myarticle";
    public static final String TABLE_NAME_SLIDE = "myarticle_slide";

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_URLTOIMAGE = "urltoimage";
    public static final String COLUMN_PUBLISHEDAT = "publishedat";
    public static final String COLUMN_QUERY = "query";
    public static final String COLUMN_TIMESTAMP = "timestamp";

    private int id;
    private String title;
    private String description;
    private String urltoimage;
    private String publishedat;
    private String query;
    private String timestamp;


    // Create table SQL query
    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_DESCRIPTION + " TEXT,"
                    + COLUMN_URLTOIMAGE + " TEXT,"
                    + COLUMN_PUBLISHEDAT + " TEXT,"
                    + COLUMN_QUERY + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public static final String CREATE_TABLE_SLIDER =
            "CREATE TABLE " + TABLE_NAME_SLIDE + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_DESCRIPTION + " TEXT,"
                    + COLUMN_URLTOIMAGE + " TEXT,"
                    + COLUMN_PUBLISHEDAT + " TEXT,"
                    + COLUMN_QUERY + " TEXT,"
                    + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                    + ")";

    public MyArticle() {
    }

    public MyArticle(int id, String title,  String description, String urltoimage, String publishedat, String query, String timestamp) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.urltoimage = urltoimage;
        this.publishedat = publishedat;
        this.query = query;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrltoimage() {
        return urltoimage;
    }

    public void setUrltoimage(String urltoimage) {
        this.urltoimage = urltoimage;
    }

    public String getPublishedat() {
        return publishedat;
    }

    public void setPublishedat(String publishedat) {
        this.publishedat = publishedat;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}