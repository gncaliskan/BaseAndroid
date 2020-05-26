package com.oyak.baseapp.rest.Response;

import com.oyak.baseapp.model.BaseResponse;
import com.oyak.baseapp.model.User;

import java.io.Serializable;

public class UserResponse extends BaseResponse implements Serializable {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
