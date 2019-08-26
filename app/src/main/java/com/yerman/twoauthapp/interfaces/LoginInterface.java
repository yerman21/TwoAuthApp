package com.yerman.twoauthapp.interfaces;

import com.yerman.twoauthapp.entity.User;

public interface LoginInterface {
    interface Model{
        void logear(String username, String password);
    }
    interface Presenter{
        void logear(String username, String password);
        void showPageHome(User user);
        void showMessageError(String message);
    }
    interface View{
        void showPageHome(User user);
        void showMessageError(String message);
    }
}
