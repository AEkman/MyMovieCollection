package com.newton.mymoviecollection.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {

    @Id
    private String imdbId;
    private String title;
    private String year;
    private String poster;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name = "user_movies",
            joinColumns = { @JoinColumn(name = "imdbId") },
            inverseJoinColumns = { @JoinColumn(name = "user_id")}
    )
    private List<User> users;

    public Movie() {
    }

    public Movie(String title, String year, String imdbId, String poster) {
        this.title = title;
        this.year = year;
        this.imdbId = imdbId;
        this.poster = poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}