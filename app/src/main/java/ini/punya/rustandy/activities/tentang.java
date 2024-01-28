package ini.punya.rustandy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import ini.punya.rustandy.R;

public class tentang extends AppCompatActivity {

    MaterialButton btnpakar, btnkembali ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang);

        btnpakar = findViewById(R.id.btnpakar);
        btnkembali = findViewById(R.id.btn);

        btnpakar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tentang.this, Pakar.class);
                startActivity(intent);
            }
        });
       btnkembali.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
            Intent intent = new Intent(tentang.this, MainActivity.class);
            startActivity(intent);
           }
       });

    }
    }
