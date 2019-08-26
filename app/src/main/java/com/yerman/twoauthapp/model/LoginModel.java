package com.yerman.twoauthapp.model;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.yerman.twoauthapp.entity.User;
import com.yerman.twoauthapp.interfaces.LoginInterface;

public class LoginModel implements LoginInterface.Model {

    private LoginInterface.Presenter presenter;
    private FirebaseFirestore db;
    private final String TAG = "(LoginModel) ";

    public LoginModel(LoginInterface.Presenter presenter){
        this.presenter = presenter;
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void logear(String username, String password) {
        final String user = username;
        final String pass = password;
        Log.d(TAG, "consultado "+user+" y "+pass);

        db.collection("user").whereEqualTo("username", user).get()
            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        if (task.getResult().isEmpty()){
                            Log.d(TAG, "No existe el usuario: "+user);
                            presenter.showMessageError("No existe el usuario '"+user+"'");
                        }
                        DocumentSnapshot document = task.getResult().getDocuments().get(0);
                        User user = document.toObject(User.class);
                        if(!user.getPass().equals(pass)) {
                            presenter.showMessageError("Password incorrecto");
                        }
                        presenter.showPageHome(user);
                    }else{
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                }
            });
    }
}
