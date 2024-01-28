package ini.punya.rustandy.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.azhar.spks.database.DatabaseHelper;
import com.google.android.material.button.MaterialButton;

import java.util.List;

import ini.punya.rustandy.R;
import ini.punya.rustandy.api.ApiClient;
import ini.punya.rustandy.api.ApiInterface;
import ini.punya.rustandy.model.HasilModul;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HasilDiagnosaActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    Toolbar toolbar;
    TextView tvGejala, tvNamaPenyakit, ts;
    ApiInterface api;
    MaterialButton btnDiagnosaUlang, btnDaftarPenyakit, btnFiturWa;

    TextView[] penyakitarray = new TextView[1];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_diagnosa);

        setStatusBar();

        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        if (databaseHelper.openDatabase())
            sqLiteDatabase = databaseHelper.getReadableDatabase();

        toolbar = findViewById(R.id.toolbar);
        tvGejala = findViewById(R.id.tvGejala);
        tvNamaPenyakit = findViewById(R.id.tvNamaPenyakit);
        btnDiagnosaUlang = findViewById(R.id.btnDiagnosaUlang);
        btnDaftarPenyakit = findViewById(R.id.btnDaftarPenyakit);
        btnFiturWa = findViewById(R.id.btnFiturWa);
        ts = findViewById(R.id.test);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        String str_hasil = getIntent().getStringExtra("HASIL");
        String kode = getIntent().getStringExtra("KODE");

        String[] gejala_terpilih = new String[0];
        String[] kode_terpilih = new String[0];
        if (str_hasil != null) {
            gejala_terpilih = str_hasil.split("#");
        }
        if (kode != null) {
            kode_terpilih = kode.split("#");
        }

        StringBuffer output_gejala_terpilih = new StringBuffer();
        int no = 1;
        for (String s_gejala_terpilih : gejala_terpilih){
            output_gejala_terpilih.append(no++)
                    .append(". ")
                    .append(s_gejala_terpilih)
                    .append("\n");
        }
        tvGejala.setText(output_gejala_terpilih);
        init(kode);
        btnDiagnosaUlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnDaftarPenyakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HasilDiagnosaActivity.this, DaftarPenyakitActivity.class);
                startActivity(intent);
            }
        });
        btnFiturWa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PhoneNumber = "6281343135876";
                String url = "https://wa.me/" + PhoneNumber;
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }


    private void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        if (on) {
            layoutParams.flags |= bits;
        } else {
            layoutParams.flags &= ~bits;
        }
        window.setAttributes(layoutParams);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void init(String key) {
        api = ApiClient.getClient().create(ApiInterface.class);
        Call<List<HasilModul>> call = api.gethasil(key);
        call.enqueue(new Callback<List<HasilModul>>() {
            @Override
            public void onResponse(Call<List<HasilModul>> call, Response<List<HasilModul>> response) {
                if (response.body() != null && response.isSuccessful()) {
                    List<HasilModul> hasilList = response.body();

                    // Loop through the list of objects and display them
                    for (HasilModul hsil : hasilList) {
                        String penya = hsil.getNamaPenyakit();
                        String persen = hsil.getPersentase();

                        // Display the result or handle as needed
                        // For example, append to a TextView or add to a list
                        tvNamaPenyakit.append(penya + " " + persen + "%\n");
                    }
                } else {
                    // Handle the case where the response is not successful
                }
            }

        @Override
        public void onFailure(Call<List<HasilModul>> call, Throwable t) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HasilDiagnosaActivity.this);
                builder.setTitle("Judul Pesan")
                        .setMessage(t.getLocalizedMessage())
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Aksi yang ingin dilakukan ketika tombol OK ditekan
                                dialogInterface.dismiss(); // Tutup dialog
                            }
                        })
                        .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Aksi yang ingin dilakukan ketika tombol Batal ditekan
                                dialogInterface.dismiss(); // Tutup dialog
                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }

}
