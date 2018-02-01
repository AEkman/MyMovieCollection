package com.newton.mymoviecollection.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "movie")
public class Movie {

//    @Id
    //    private Long id;
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private String imdbId;
    private String title;
    private String year;
    private String poster;

    public Movie() {
    }

    public Movie(String title, String year, String imdbId, String poster) {
        this.title = title;
        this.year = year;
        this.imdbId = imdbId;
        this.poster = poster;
    }

    // Getters and setters
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

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
