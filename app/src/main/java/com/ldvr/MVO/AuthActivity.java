package com.ldvr.MVO;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.ldvr.MVO.databinding.ActivityAuthBinding;

public class AuthActivity extends AppCompatActivity {

    ActivityAuthBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setup();
    }

    private void setup() {

        FacadeSession.OnAuthListener onAuthListener =  new FacadeSession.OnAuthListener() {
            @Override
            public void onSuccess() {
                Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                startActivity(intent);
                working(false);
            }
            @Override
            public void onError(Exception e) {
                if(e instanceof FirebaseAuthUserCollisionException){
                    Toast.makeText(AuthActivity.this,"El correo ingresado ya existe",Toast.LENGTH_SHORT).show();
                }
                else if(e instanceof FirebaseAuthWeakPasswordException){
                    Toast.makeText(AuthActivity.this,"La contraseña debe tener al menos 6 caracteres",Toast.LENGTH_SHORT).show();
                }
                else if(e instanceof FirebaseAuthInvalidCredentialsException){
                    Toast.makeText(AuthActivity.this,"El correo ingresado no es valido",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(AuthActivity.this,"Error al crear la cuenta",Toast.LENGTH_SHORT).show();
                }
                working(false);
            }
        };

        binding.createAccount.setOnClickListener((v)->{

            String[] fields = getEmailFields();
            if(fields == null){
                Toast.makeText(this,"Por favor ingrese un correo y una contraseña",Toast.LENGTH_SHORT).show();
                return;
            }
            working(true);
            FacadeSession.createAccount(fields[0], fields[1],onAuthListener);
        });
        binding.signIn.setOnClickListener((v)->{

            String[] fields = getEmailFields();
            if(fields == null){
                Toast.makeText(this,"Por favor ingrese un correo y una contraseña",Toast.LENGTH_SHORT).show();
                return;
            }
            working(true);
            FacadeSession.signIn(fields[0], fields[1],onAuthListener);
        });
    }

    private String[] getEmailFields(){
        String email = binding.email.getText() == null? "" : binding.email.getText().toString();
        String password = binding.password.getText() == null? "" : binding.password.getText().toString();

        if(email.isEmpty() || password.isEmpty()){
            return null;
        }
        else{
            return new String[]{email,password};
        }

    }
    private void working(boolean state){
        binding.signIn.setEnabled(!state);
        binding.createAccount.setEnabled(!state);
        binding.progressBar.setVisibility(state ? View.VISIBLE : View.GONE);
    }

}

