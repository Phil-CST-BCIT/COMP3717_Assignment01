package com.bcit.comp3717.assignment01;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class News {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("totalResults")
    @Expose
    private int totalResults;

    @SerializedName("articles")
    @Expose
    private ArrayList<Article> articles;

    /**
     * getter
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * getter
     * @return totalResult
     */
    public int getTotalResults() {
        return totalResults;
    }

    /**
     * getter
     * @return articles
     */
    public ArrayList<Article> getArticles() {
        return articles;
    }

    /**
     * setter
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * setter
     * @param totalResults
     */
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * setter
     * @param articles
     */
    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }

    public String toString() {
        return "News [ status = " + status + ", totalResults = " + totalResults + " ]";
    }

}
