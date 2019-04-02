package com.example.demo.news;

public class News {

    private String newsTitle, thumbnailUrl;
    private String newsDetails;
    private String newsDetailsUrl;

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String name) {
        this.newsTitle = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getNewsDetails() {
        return newsDetails;
    }

    public void setNewsDetails(String newsDetails) {
        this.newsDetails = newsDetails;
    }

    public String getNewsDetailsUrl() {
        return newsDetailsUrl;
    }

    public void setNewsDetailsUrl(String newsDetailsUrl) {
        this.newsDetailsUrl = newsDetailsUrl;
    }

}
