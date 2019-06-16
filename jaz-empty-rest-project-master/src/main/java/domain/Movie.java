package domain;

import java.util.List;

public class Movie {
    private int id;
    private String title;
    private int year;
    private String genre;
    private String description;
    private double rate;
    private List<Comment> comments;

    public int getId(){
        return id;
    }
    public void setId (int id){
        this.id = id;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title=title;
    }
    public int getYear(){
        return year;
    }
    public void setYear(int year){
        this.year= this.year;
    }
    public String getGenre(){
        return genre;
    }
    public void setGenre (String genre){
        this.genre=genre;
    }
    public String getDescription (){
        return description;
    }
    public void setDescription (String description){
        this.description=description;
    }
    public double getRate(){
        return rate;
    }
    public void setRate (double rate){
        this.rate=rate;
    }
    public List<Comment> getComments(){
        return comments;
    }
    public void setComments(List<Comment> comments){
        this.comments=comments;
    }
}
