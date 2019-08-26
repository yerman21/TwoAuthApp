package com.yerman.twoauthapp.presenter;

import com.yerman.twoauthapp.entity.User;
import com.yerman.twoauthapp.interfaces.LoginInterface;
import com.yerman.twoauthapp.model.LoginModel;

public class LoginPresenter implements LoginInterface.Presenter {

    private LoginInterface.View view;
    private LoginInterface.Model model;

    public LoginPresenter(LoginInterface.View view){
        this.view = view;
        this.model = new LoginModel(this);
    }

    @Override
    public void logear(String username, String password) {
        if(username.isEmpty() || password.isEmpty()) {
            view.showMessageError("El username y el password son requeridos");
        }
        model.logear(username, password);
    }

    @Override
    public void showPageHome(User user) {
        view.showPageHome(user);
    }

    @Override
    public void showMessageError(String message) {
        view.showMessageError(message);
        return;
    }
}
