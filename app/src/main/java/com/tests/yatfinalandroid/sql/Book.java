package com.tests.yatfinalandroid.sql;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Book {
    @PrimaryKey(autoGenerate = true)
    public int localId;

    @ColumnInfo(name = "book_title")
    public String bookTitle;

    @ColumnInfo(name = "image_url")
    public String imageUrl;

    @ColumnInfo(name = "serverId")
    public String serverId;
}

