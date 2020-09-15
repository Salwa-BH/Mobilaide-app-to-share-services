package com.example.mobilaide;

import java.util.Date;

public class Message {
    String id;
    String username;
    String date;
    String categorie;
    String type;
    String description;

    public Message(){

    }

    public Message(String id, String username, String date, String categorie, String type, String description) {
        this.id = id;
        this.username = username;
        this.date = date;
        this.categorie = categorie;
        this.type = type;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDate() {
        return date;
    }

    public String getCategorie() {
        return categorie;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public String toString(){
        return   "\n" + this.username.toUpperCase() + "\n\n   at " + date.toString() + "\n\n\t" +  type + "\n\n" + description+"\n";}

}
