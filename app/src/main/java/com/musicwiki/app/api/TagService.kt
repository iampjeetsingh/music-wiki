package com.musicwiki.app.api

import com.musicwiki.app.BuildConfig
import com.musicwiki.app.models.responses.*
import retrofit2.http.GET
import retrofit2.http.Query

interface TagService {
    @GET("/2.0")
    suspend fun getTopTags(
        @Query("method") method:String="tag.getTopTags",
        @Query("api_key") apiKey:String=BuildConfig.API_KEY,
        @Query("format") format:String="json"
    ): TopTagsResponse

    @GET("/2.0")
    suspend fun getTagInfo(
        @Query("tag") tag:String,
        @Query("method") method:String="tag.getInfo",
        @Query("api_key") apiKey:String=BuildConfig.API_KEY,
        @Query("format") format:String="json"
    ): TagInfoResponse

    @GET("/2.0")
    suspend fun getTopAlbums(
        @Query("tag") tag:String,
        @Query("method") method:String="tag.getTopAlbums",
        @Query("api_key") apiKey:String=BuildConfig.API_KEY,
        @Query("format") format:String="json"
    ): TopAlbumsResponse

    @GET("/2.0")
    suspend fun getTagArtists(
        @Query("tag") tag:String,
        @Query("method") method:String="tag.getTopArtists",
        @Query("api_key") apiKey:String=BuildConfig.API_KEY,
        @Query("format") format:String="json"
    ): TopArtistResponse

    @GET("/2.0")
    suspend fun getTagTracks(
        @Query("tag") tag:String,
        @Query("method") method:String="tag.getTopTracks",
        @Query("api_key") apiKey:String=BuildConfig.API_KEY,
        @Query("format") format:String="json"
    ): TopTracksResponse
}