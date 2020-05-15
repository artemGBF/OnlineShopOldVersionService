package com.gbf.onlineshop.test;

import com.gbf.onlineshop.model.User;

public class CheckFukingReflection {
    private Long id;
    private User user;

    public CheckFukingReflection(Long id, User user) {
        this.id = id;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CheckFukingReflection{" +
                "id=" + id +
                ", user=" + user+
                '}';
    }
}
