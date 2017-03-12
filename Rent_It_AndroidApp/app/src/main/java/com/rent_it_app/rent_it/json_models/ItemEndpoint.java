package com.rent_it_app.rent_it.json_models;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Miz on 2/1/17.
 */

public interface ItemEndpoint {

    // Request method and URL specified in the annotation
    // Callback for the parsed response is the last parameter

    /*@GET("api/items")
    Call<List<Item>> getItems(*//*@Path("uid") String uid*//*);*/
    @GET("api/items")
    Call<ArrayList<Item>> getItems(/*@Path("uid") String uid*/);

    @GET("api/items/category/{category}")
    Call<ArrayList<Item>> getItemsByCategory(@Path("category") String category);

    /*@GET("group/{id}/users")
    Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);*/

    @POST("api/items")
    Call<Item> addItem(@Body Item item);

}
