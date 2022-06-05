package com.telegramBot.repo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserFromTelegram {
    @Id

    @GeneratedValue(strategy= GenerationType.AUTO)

    private Integer id;
    private String firstName;

    public UserFromTelegram(String firstName) {
        this.firstName = firstName;
    }

    public UserFromTelegram() {};
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
