package com.ldvr.MVO;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
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
        binding.createAccount.setOnClickListener((v)->{

            if(binding.email.getText().toString().isEmpty() && binding.password.getText().toString().isEmpty())
                return;
            working(true);
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(binding.email.getText().toString(), binding.password.getText().toString())
                    .addOnCompleteListener((result)->{
                        if(result.isSuccessful()){
                            Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{
                            Exception e =   result.getException();
                            if(e != null){
                                if(e instanceof FirebaseAuthUserCollisionException){
                                    Toast.makeText(this,"El correo ingresado ya existe",Toast.LENGTH_SHORT).show();
                                }
                                else if(e instanceof FirebaseAuthWeakPasswordException){
                                    Toast.makeText(this,"La contraseña debe tener al menos 6 caracteres",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(this,"Error al crear la cuenta",Toast.LENGTH_SHORT).show();
                                }
                            }
                            Log.e("ERROR",result.getException().toString());

                        }
                        working(false);
                    });
        });
        binding.signIn.setOnClickListener((v)->{

            if(binding.email.getText().toString().isEmpty() && binding.password.getText().toString().isEmpty())
                return;
            working(true);
            FirebaseAuth.getInstance().signInWithEmailAndPassword(binding.email.getText().toString(), binding.password.getText().toString())
                    .addOnCompleteListener((result)->{
                        if(result.isSuccessful()){
                            Intent intent = new Intent(AuthActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else{

                            Toast.makeText(this,"Usuario no encontrado o contraseña incorrecta",Toast.LENGTH_SHORT).show();
                        }
                        working(false);
                    });
        });
    }

    private void working(boolean state){
        binding.signIn.setEnabled(!state);
        binding.createAccount.setEnabled(!state);
        binding.progressBar.setVisibility(state ? View.VISIBLE : View.GONE);
    }

}

