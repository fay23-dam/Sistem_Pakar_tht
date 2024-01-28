package ini.punya.rustandy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;

import ini.punya.rustandy.R;
public class Pakar extends AppCompatActivity {

    MaterialButton btnkembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pakar);

        btnkembali = findViewById(R.id.btnkembali1);

        btnkembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Pakar.this, tentang.class);
                startActivity(intent);

            }
        });
    }
}