package com.example.datadiri;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtNama,edtAlamat, edtTelepon, edtHobi, edtKeinginan;
    private Spinner jk;
    private Button btnHasil, edtTtl;
    private TextView dataResult;
    private int mTahun, mBulan, mHari;
    private static final String STATE_RESULT = "state_result";

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, dataResult.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNama =(EditText) findViewById(R.id.edit_nama);
        edtTtl =(Button) findViewById(R.id.edit_ttl);
        edtAlamat =(EditText) findViewById(R.id.edit_alamat);
        edtTelepon =(EditText) findViewById(R.id.edit_telepon);
        edtHobi =(EditText) findViewById(R.id.edit_hobi);
        edtKeinginan =(EditText) findViewById(R.id.edit_keinginan);
        btnHasil =(Button) findViewById(R.id.btn_hasil);
        dataResult =(TextView) findViewById(R.id.data_result);
        jk =(Spinner) findViewById(R.id.edit_jk);

        List<String> listGender = new ArrayList<>();
        listGender.add("Pria");
        listGender.add("Wanita");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listGender);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jk.setAdapter(adapter);

        edtTtl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal =  Calendar.getInstance();
                mTahun = cal.get(Calendar.YEAR);
                mBulan = cal.get(Calendar.MONTH);
                mHari = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDateDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfmonth) {
                        edtTtl.setText(String.valueOf(dayOfmonth)+" - "+(month)+" - "+(year));
                    }
                },mTahun,mBulan,mHari);
                mDateDialog.setTitle("TTL");
                mDateDialog.show();
            }
        });
        btnHasil.setOnClickListener(this);

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            dataResult.setText(result);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_hasil) {
            String inputNama = edtNama.getText().toString().trim();
            String inputTtl = edtTtl.getText().toString().trim();
            String inputAlamat = edtAlamat.getText().toString().trim();
            String inputTelepon = edtTelepon.getText().toString().trim();
            String inputHobi = edtHobi.getText().toString().trim();
            String inputKeinginan = edtKeinginan.getText().toString().trim();

            boolean isEmpetyFields = false;
            boolean isInvalidDouble = false;

            if (TextUtils.isEmpty(inputNama)) {
                isEmpetyFields = true;
                edtNama.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(inputTtl)) {
                isEmpetyFields = true;
                edtTtl.setError("Field ini tidak boleh kosong");
            }

            if (TextUtils.isEmpty(inputAlamat)) {
                isEmpetyFields = true;
                edtAlamat.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(inputTelepon)) {
                isEmpetyFields = true;
                edtTelepon.setError("Field ini tidak boleh kosong");
            }
            if (TextUtils.isEmpty(inputHobi)) {
                isEmpetyFields = true;
                edtHobi.setError("Field ini tidak boleh kosong");
            }if (TextUtils.isEmpty(inputKeinginan)) {
                isEmpetyFields = true;
                edtKeinginan.setError("Field ini tidak boleh kosong");
            }
            if (!isEmpetyFields) {

                dataResult.setText("Hello " + edtNama.getText().toString() + "You have complete this form,see more your profil\nTanggal lahir = " + edtTtl.getText().toString() +
                        ",\nAlamat = " + edtAlamat.getText().toString() + ",\nKontak = " + edtTelepon.getText().toString() + ".\nHobi = " +
                        edtHobi.getText().toString() + "Keinginan = " + edtKeinginan.getText().toString() + "Application can running");
            }
        }
    }
}
