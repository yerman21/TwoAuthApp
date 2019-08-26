package com.yerman.twoauthapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yerman.twoauthapp.view.MainActivity;
import com.yerman.twoauthapp.R;
import com.yerman.twoauthapp.entity.User;
import com.yerman.twoauthapp.interfaces.LoginInterface;
import com.yerman.twoauthapp.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, LoginInterface.View {
    private EditText etext_username;
    private EditText etext_pass;
    private EditText etext_phone;
    private Button btn_login;
    private LoginInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etext_username = findViewById(R.id.etext_username);
        etext_pass = findViewById(R.id.etext_pass);
        etext_phone = findViewById(R.id.etext_phone);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this);

        presenter = new LoginPresenter(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                presenter.logear(
                        etext_username.getText().toString(),
                        etext_pass.getText().toString()
                );
                break;
        }
    }

    @Override
    public void showPageHome(User user) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("userData", user);
        startActivity(intent);
    }

    @Override
    public void showMessageError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
