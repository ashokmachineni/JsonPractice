package com.ashok.jsonpractice;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ashok on 9/27/16.
 */
public class ItemObject {
    @SerializedName("title")
    private String title;
    /* @SerializedName("category")
     private String category;*/
    @SerializedName("image")
    private String image;
    @SerializedName("url")
    private String url;
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

    public ItemObject(String title, String image, String url) {
        this.title = title;
        //this.category = category;
        this.image = image;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /* public String getCategory() {
         return category;
     }

     public void setCategory(String category) {
         this.category = category;
     }
 */
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
