package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private EditText editTextMaNV, editTextTenNV, editTextSDT, editTextEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhanvien);
        databaseHelper = new DatabaseHelper(this, null, null, 1);

        Button buttonAddEmployee = findViewById(R.id.buttonAddEmployee);
        buttonAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextMaNV = findViewById(R.id.editTextMaNV);
                editTextTenNV = findViewById(R.id.editTextTenNV);
                editTextSDT = findViewById(R.id.editTextSDT);
                editTextEmail = findViewById(R.id.editTextEmail);

                addEmployee();
            }
        });

        Button buttonLoadEmployee = findViewById(R.id.buttonShowLog);
        buttonLoadEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Employee",  databaseHelper.loadDataHandler("NhanVien"));
            }
        });
    }

    private void addEmployee() {
        String maNV = editTextMaNV.getText().toString().trim();
        String tenNV = editTextTenNV.getText().toString().trim();
        String sdt = editTextSDT.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();

        long result = databaseHelper.insertNhanVien(maNV, tenNV, sdt, email);
        if (result != -1) {
            Toast.makeText(this, "Thêm nhân viên thành công", Toast.LENGTH_SHORT).show();
            clearInputs();
        } else {
            Toast.makeText(this, "Thêm nhân viên thất bại", Toast.LENGTH_SHORT).show();
        }
    }

    private void clearInputs() {
        editTextMaNV.setText("");
        editTextTenNV.setText("");
        editTextSDT.setText("");
        editTextEmail.setText("");
    }
}