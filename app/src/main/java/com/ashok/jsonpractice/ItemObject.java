package com.ashok.jsonpractice;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ashok on 9/27/16.
 */
public class ItemObject {
    @SerializedName("title")
    private String title;
    @SerializedName("category")
    private String category;
    @SerializedName("poster")
    private String poster;
    @SerializedName("videoUrl")
    private String videoUrl;
    /*public ItemObject(String title, String category, String poster) {
        this.songTitle = songTitle;
        this.songYear = songYear;
        this.songAuthor = songAuthor;
    }
    public String getSongTitle() {
        return songTitle;
    }
    public String getSongYear() {
        return songYear;
    }
    public String getSongAuthor() {
        return songAuthor;
    }*/

    public ItemObject(String title, String category, String poster, String videoUrl) {
        this.title = title;
        this.category = category;
        this.poster = poster;
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
