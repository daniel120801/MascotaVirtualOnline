package com.ldvr.MVO;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VerifierOverlayPermission extends AppCompatActivity {
    private final ActivityResultLauncher<Intent> overlayPermissionLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                // Callback que se ejecuta cuando el usuario regresa de la pantalla de configuración
                if (Settings.canDrawOverlays(this)) {
                    Toast.makeText(this,"Permiso concedido",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this,"Permiso denegado",Toast.LENGTH_SHORT).show();
                    // Permiso no concedido, manejar según sea necesario
                }
                finish();
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!Settings.canDrawOverlays(this)) {
            // Crear intent para solicitar permiso
            Intent intent = new Intent(
                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName())
            );

            // Lanzar la actividad usando el launcher registrado
            overlayPermissionLauncher.launch(intent);
        } else {
            Toast.makeText(this,"Permiso ya concedido",Toast.LENGTH_SHORT).show();
         finish();
        }

    }




}