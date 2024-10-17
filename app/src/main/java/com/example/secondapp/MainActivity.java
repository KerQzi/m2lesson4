package com.example.secondapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPassword;
    private Button btnLogin;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        editTextEmail = findViewById(R.id.etEmail);
        editTextPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        editTextEmail.addTextChangedListener(textWatcher);
        editTextPassword.addTextChangedListener(textWatcher);

        hasWrittenChecker();

        btnLogin.setOnClickListener(v -> {
            checkInput(v);
        });
    }

    @SuppressLint("ResourceAsColor")
    private void hasWrittenChecker() {
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (!email.isEmpty() && !password.isEmpty()) {
            btnLogin.setBackgroundColor(getResources().getColor(R.color.orange));
            btnLogin.setEnabled(true);
        } else {
            btnLogin.setBackgroundColor(getResources().getColor(R.color.gray));
            btnLogin.setEnabled(false);
        }
    }

    private void checkInput(View v) {
        hasWrittenChecker();

        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (email.equals("admin") && password.equals("admin")) {
            Snackbar.make(v, "Вы успешно зарегистрировались", Snackbar.LENGTH_LONG).show();
            hideAll();
        } else {
            Snackbar.make(v, "Неправильный логин и пароль", Snackbar.LENGTH_LONG).show();
        }
    }

    private void hideAll() {
        findViewById(R.id.tvLogin).setVisibility(View.GONE);
        findViewById(R.id.tvSubtext).setVisibility(View.GONE);
        findViewById(R.id.login_container).setVisibility(View.GONE);

    }

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            hasWrittenChecker();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
