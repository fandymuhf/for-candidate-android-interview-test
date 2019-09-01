package com.tokopedia.testproject.problems.news.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.tokopedia.testproject.problems.news.model.MyArticle;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "myarticle_db";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create table
        db.execSQL(MyArticle.CREATE_TABLE);
        db.execSQL(MyArticle.CREATE_TABLE_SLIDER);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + MyArticle.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MyArticle.TABLE_NAME_SLIDE);

        // Create tables again
        onCreate(db);
    }

    public void recreateDB() {
        SQLiteDatabase db = this.getWritableDatabase();
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + MyArticle.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MyArticle.TABLE_NAME_SLIDE);

        // Create tables again
        onCreate(db);
    }

    public void insertAllNews(String title, String desc, String urltoimage, String pubat, String query) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(MyArticle.COLUMN_TITLE, title);
        values.put(MyArticle.COLUMN_DESCRIPTION, desc);
        values.put(MyArticle.COLUMN_URLTOIMAGE, urltoimage);
        values.put(MyArticle.COLUMN_PUBLISHEDAT, pubat);
        values.put(MyArticle.COLUMN_QUERY, query);

        // insert row
        db.insert(MyArticle.TABLE_NAME, null, values);

        // close db connection
        db.close();
    }

    public void insertAllSlider(String title, String desc, String urltoimage, String pubat, String query) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(MyArticle.COLUMN_TITLE, title);
        values.put(MyArticle.COLUMN_DESCRIPTION, desc);
        values.put(MyArticle.COLUMN_URLTOIMAGE, urltoimage);
        values.put(MyArticle.COLUMN_PUBLISHEDAT, pubat);
        values.put(MyArticle.COLUMN_QUERY, query);

        // insert row
        db.insert(MyArticle.TABLE_NAME_SLIDE, null, values);

        // close db connection
        db.close();
    }

    public List<MyArticle> getArticle(String query) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();
        List<MyArticle> myArticles = new ArrayList<>();

        Cursor cursor = db.query(MyArticle.TABLE_NAME,
                new String[]{MyArticle.COLUMN_ID, MyArticle.COLUMN_TITLE, MyArticle.COLUMN_DESCRIPTION, MyArticle.COLUMN_URLTOIMAGE, MyArticle.COLUMN_PUBLISHEDAT, MyArticle.COLUMN_QUERY,MyArticle.COLUMN_TIMESTAMP},
                MyArticle.COLUMN_QUERY + "=?",
                new String[]{String.valueOf(query)}, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MyArticle myArticle = new MyArticle();
                myArticle.setId(cursor.getInt(cursor.getColumnIndex(MyArticle.COLUMN_ID)));
                myArticle.setTitle(cursor.getString(cursor.getColumnIndex(MyArticle.COLUMN_TITLE)));
                myArticle.setDescription(cursor.getString(cursor.getColumnIndex(MyArticle.COLUMN_DESCRIPTION)));
                myArticle.setUrltoimage(cursor.getString(cursor.getColumnIndex(MyArticle.COLUMN_URLTOIMAGE)));
                myArticle.setPublishedat(cursor.getString(cursor.getColumnIndex(MyArticle.COLUMN_PUBLISHEDAT)));
                myArticle.setTimestamp(cursor.getString(cursor.getColumnIndex(MyArticle.COLUMN_TIMESTAMP)));

                myArticles.add(myArticle);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return myArticles;
    }

    public List<MyArticle> getArticleSlider(String query) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();
        List<MyArticle> myArticles = new ArrayList<>();

        Cursor cursor = db.query(MyArticle.TABLE_NAME_SLIDE,
                new String[]{MyArticle.COLUMN_ID, MyArticle.COLUMN_TITLE, MyArticle.COLUMN_DESCRIPTION, MyArticle.COLUMN_URLTOIMAGE, MyArticle.COLUMN_PUBLISHEDAT, MyArticle.COLUMN_QUERY,MyArticle.COLUMN_TIMESTAMP},
                MyArticle.COLUMN_QUERY + "=?",
                new String[]{String.valueOf(query)}, null, null, null, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MyArticle myArticle = new MyArticle();
                myArticle.setId(cursor.getInt(cursor.getColumnIndex(MyArticle.COLUMN_ID)));
                myArticle.setTitle(cursor.getString(cursor.getColumnIndex(MyArticle.COLUMN_TITLE)));
                myArticle.setDescription(cursor.getString(cursor.getColumnIndex(MyArticle.COLUMN_DESCRIPTION)));
                myArticle.setUrltoimage(cursor.getString(cursor.getColumnIndex(MyArticle.COLUMN_URLTOIMAGE)));
                myArticle.setPublishedat(cursor.getString(cursor.getColumnIndex(MyArticle.COLUMN_PUBLISHEDAT)));
                myArticle.setTimestamp(cursor.getString(cursor.getColumnIndex(MyArticle.COLUMN_TIMESTAMP)));

                myArticles.add(myArticle);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return myArticles;
    }

    public List<MyArticle> getAllArticle() {
        List<MyArticle> myArticles = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + MyArticle.TABLE_NAME + " ORDER BY timestamp DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MyArticle myArticle = new MyArticle();
                myArticle.setId(cursor.getInt(cursor.getColumnIndex(MyArticle.COLUMN_ID)));
                myArticle.setTitle(cursor.getString(cursor.getColumnIndex(MyArticle.COLUMN_TITLE)));
                myArticle.setDescription(cursor.getString(cursor.getColumnIndex(MyArticle.COLUMN_DESCRIPTION)));
                myArticle.setUrltoimage(cursor.getString(cursor.getColumnIndex(MyArticle.COLUMN_URLTOIMAGE)));
                myArticle.setPublishedat(cursor.getString(cursor.getColumnIndex(MyArticle.COLUMN_PUBLISHEDAT)));
                myArticle.setTimestamp(cursor.getString(cursor.getColumnIndex(MyArticle.COLUMN_TIMESTAMP)));

                myArticles.add(myArticle);
            } while (cursor.moveToNext());
        }
        cursor.close();

        // close db connection
        db.close();

        // return notes list
        return myArticles;
    }

    public int getArticleCount() {
        String countQuery = "SELECT  * FROM " + MyArticle.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

    public int getArticleCountKeyword(String mykeyword) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor= db.rawQuery("select count(*) from "+MyArticle.TABLE_NAME+" where query='" + mykeyword + "'", null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
        cursor.close();

        // return count
        return count;
    }

    public int getArticleSliderCountKeyword(String mykeyword) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor= db.rawQuery("select count(*) from "+MyArticle.TABLE_NAME_SLIDE+" where query='" + mykeyword + "'", null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
        cursor.close();

        // return count
        return count;
    }

    public int updateArticle(MyArticle article) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MyArticle.COLUMN_TITLE, article.getTitle());

        // updating row
        return db.update(MyArticle.TABLE_NAME, values, MyArticle.COLUMN_ID + " = ?",
                new String[]{String.valueOf(article.getId())});
    }

    public void deleteArticle(String query) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MyArticle.TABLE_NAME, MyArticle.COLUMN_QUERY + " = ?",
                new String[]{String.valueOf(query)});
        db.close();
    }

    public void deleteArticleSlider(String query) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MyArticle.TABLE_NAME_SLIDE, MyArticle.COLUMN_QUERY + " = ?",
                new String[]{String.valueOf(query)});
        db.close();
    }

    public void deleteAllArticle() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ MyArticle.TABLE_NAME);
        db.close();
    }
}