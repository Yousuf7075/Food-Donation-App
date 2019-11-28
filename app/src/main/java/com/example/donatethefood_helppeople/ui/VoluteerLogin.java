package com.example.donatethefood_helppeople.ui;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.donatethefood_helppeople.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.snackbar.SnackbarContentLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class VoluteerLogin extends Fragment {
    FirebaseAuth firebaseAuth;
    FirebaseUser currentUser;
    TextInputEditText emailIn, passwordIn;
    Button loginButton, signUpBtn;
    Context context;
    private VolunteerLoginSuccessfulListener listener;
    LinearLayout linearLayout;

    public VoluteerLogin() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.listener = (VolunteerLoginSuccessfulListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_voluteer_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        emailIn = view.findViewById(R.id.emailInput);
        passwordIn = view.findViewById(R.id.passwordInput);
        loginButton = view.findViewById(R.id.loginBtn);
        signUpBtn = view.findViewById(R.id.signUp);
        linearLayout = view.findViewById(R.id.snacbarView);

        firebaseAuth = FirebaseAuth.getInstance();

        //login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String email = emailIn.getText().toString();
                String pass = passwordIn.getText().toString();
                firebaseAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            currentUser =  firebaseAuth.getCurrentUser();
                            Toast.makeText(getActivity(), "login successful", Toast.LENGTH_SHORT).show();
                            listener.loginSuccessful();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("failed", e.getLocalizedMessage());
                        Snackbar snackbar = Snackbar.make(linearLayout,""+e.getLocalizedMessage(),Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }
                });
            }
        });

        //signup
        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.goToRegistration();
            }
        });
    }
    public interface VolunteerLoginSuccessfulListener{
        void loginSuccessful();
        void goToRegistration();
    }
}
