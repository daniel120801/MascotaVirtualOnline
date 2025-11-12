package com.ldvr.MVO;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.ldvr.MVO.databinding.ActivityMainBinding;
import com.ldvr.MVO.petsModule.Pet;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    Pet pet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        FacadeSession.getPets(new FacadeSession.OnGetPetsListener() {
            @Override
            public void onSuccess(List<Pet> pets) {
                Log.d("FIRESTORE", "Pets: " + pets);
                loadPets(pets);
            }

            @Override
            public void onError(Exception e) {
                Log.e("FIRESTORE", "Error getting pets", e);

            }
        });

        if(!Settings.canDrawOverlays(this)){
            Intent ix = new Intent(this, VerifierOverlayPermission.class);
            startActivity(ix);
        }

        Intent i = new Intent(this, OverlayService.class);
        startForegroundService(i);

    }

    private void loadPets(List<Pet> pets) {
        PetsAdapterList petsAdapterList = new PetsAdapterList(pets);
        binding.list.setAdapter(petsAdapterList);

    }

    private void updatePet() {

        FacadeSession.feedPet(pet, 12);


    }
}