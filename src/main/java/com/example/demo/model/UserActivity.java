package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yuwang on 5/26/17.
 */

@Entity(name = "User_Activity")
@Table(name = "User_Activity")
public class UserActivity {


    private String username;
    private String acountRelatedEvent;
    private Date date;
    private String searchContent;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public UserActivity() {
    }

    public UserActivity(String username, String event, Date date) {
        this.username = username;
        this.acountRelatedEvent = event;
        this.date = date;
    }


    public UserActivity(String username, String searchContent) {
        this.searchContent = searchContent;
        this.username = username;
    }

    public String getSearchContent() {
        return searchContent;
    }

    public void setSearchContent(String searchContent) {
        this.searchContent = searchContent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEvent() {
        return acountRelatedEvent;
    }

    public void setEvent(String event) {
        this.acountRelatedEvent = event;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
