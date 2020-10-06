package com.tests.yatfinalandroid.sql;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDao {
    @Query("SELECT * FROM book")
    List<Book> getAll();

    @Query("SELECT * FROM book WHERE localId IN (:userIds)")
    List<Book> loadAllByIds(int[] userIds);

    @Insert
    void insertAll(Book... users);

    @Delete
    void delete(Book user);
}
