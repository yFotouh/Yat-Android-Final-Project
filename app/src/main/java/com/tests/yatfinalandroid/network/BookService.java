package com.tests.yatfinalandroid.network;

import com.tests.yatfinalandroid.dto.webdto.BookResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BookService {
    @GET("books/v1/volumes")
    Call<BookResponse> getBookByName(@Query("q") String query);

}

