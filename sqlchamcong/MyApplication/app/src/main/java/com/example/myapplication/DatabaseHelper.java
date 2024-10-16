package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;

public class DatabaseHelper extends SQLiteOpenHelper {

    // các biến mô tả cơ sở dữ liệu
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ChamCong.db";

    private static final String TABLE_NHANVIEN = "NhanVien";
    private static final String COLUMN_MANHANVIEN = "MaNhanVien";
    private static final String COLUMN_TENNHANVIEN = "TenNhanVien";
    private static final String COLUMN_SDT = "SDT";
    private static final String COLUMN_EMAIL_NV = "Email_NV";

    // Table LoaiDonTu
    private static final String TABLE_LOAIDONTU = "LoaiDonTu";
    private static final String COLUMN_MALOAI_DONTU = "MaLoaiDonTu";
    private static final String COLUMN_TENLOAIDONTU = "TenLoaiDonTu";

    // Table CaChamCong
    private static final String TABLE_CACHAMCONG = "CaChamCong";
    private static final String COLUMN_MACA = "MaCa";
    private static final String COLUMN_TENCACHA = "TenCa";
    private static final String COLUMN_THOIGIANBATDAU = "ThoiGianBatDau";
    private static final String COLUMN_THOIGIANKETTHUC = "ThoiGianKetThuc";

    // Table TaiKhoan
    private static final String TABLE_TAIKHOAN = "TaiKhoan";
    private static final String COLUMN_MATKHAU = "MatKhau";
    private static final String COLUMN_HOTEN = "HoTen";


    // Table DonTu
    private static final String TABLE_DONTU = "DonTu";
    private static final String COLUMN_MADONTU = "MaDonTu";
    private static final String COLUMN_THOIGIANTAO = "ThoiGianTao";
    private static final String COLUMN_TRANGTHAI = "TrangThai";
    private static final String COLUMN_EMAIL_NV_DONTU = "Email_NV";
    private static final String COLUMN_PHANQUYEN = "PhanQuyen";

    // Table ChamCong
    private static final String TABLE_CHAMCONG = "ChamCong";
    private static final String COLUMN_MACHAMCONG = "MaChamCong";
    private static final String COLUMN_LOAICHAMCONG = "LoaiChamCong";
    private static final String COLUMN_THOIGIANTRE = "ThoiGianTre";
    private static final String COLUMN_MACA_CHAMCONG = "MaCa";

    private Context context;
    //phương thức khởi tạo
    public DatabaseHelper(Context context, String name,
                       SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //thực thi truy vấn
        db.execSQL(CREATE_TABLE_NHANVIEN);
        db.execSQL(CREATE_TABLE_LOAIDONTU);
        db.execSQL(CREATE_TABLE_CACHAMCONG);
        db.execSQL(CREATE_TABLE_TAIKHOAN);
        db.execSQL(CREATE_TABLE_DONTU);
        db.execSQL(CREATE_TABLE_CHAMCONG);
    }

    private static final String CREATE_TABLE_NHANVIEN = "CREATE TABLE " + TABLE_NHANVIEN + " ("
            + COLUMN_MANHANVIEN + " VARCHAR(10) PRIMARY KEY, "
            + COLUMN_TENNHANVIEN + " VARCHAR(100), "
            + COLUMN_SDT + " VARCHAR(15), "
            + COLUMN_EMAIL_NV + " VARCHAR(100) UNIQUE)";

    private static final String CREATE_TABLE_LOAIDONTU = "CREATE TABLE " + TABLE_LOAIDONTU + " ("
            + COLUMN_MALOAI_DONTU + " VARCHAR(10) PRIMARY KEY, "
            + COLUMN_TENLOAIDONTU + " VARCHAR(100))";

    private static final String CREATE_TABLE_CACHAMCONG = "CREATE TABLE " + TABLE_CACHAMCONG + " ("
            + COLUMN_MACA + " VARCHAR(10) PRIMARY KEY, "
            + COLUMN_TENCACHA + " VARCHAR(100), "
            + COLUMN_THOIGIANBATDAU + " TIME, "
            + COLUMN_THOIGIANKETTHUC + " TIME)";

    private static final String CREATE_TABLE_TAIKHOAN = "CREATE TABLE " + TABLE_TAIKHOAN + " ("
            + COLUMN_MATKHAU + " VARCHAR(255), "
            + COLUMN_HOTEN + " VARCHAR(100), "
            + COLUMN_EMAIL_NV_DONTU + " VARCHAR(100), "
            + "PRIMARY KEY (" + COLUMN_EMAIL_NV_DONTU + "), "
            + "FOREIGN KEY (" + COLUMN_EMAIL_NV_DONTU + ") REFERENCES " + TABLE_NHANVIEN + "(" + COLUMN_EMAIL_NV + "))";

    private static final String CREATE_TABLE_DONTU = "CREATE TABLE " + TABLE_DONTU + " ("
            + COLUMN_MADONTU + " VARCHAR(10) PRIMARY KEY, "
            + COLUMN_THOIGIANTAO + " DATETIME, "
            + COLUMN_TRANGTHAI + " VARCHAR(50), "
            + COLUMN_MALOAI_DONTU + " VARCHAR(10), "
            + COLUMN_EMAIL_NV_DONTU + " VARCHAR(100), "
            + COLUMN_PHANQUYEN + " VARCHAR(10), "
            + "FOREIGN KEY (" + COLUMN_MALOAI_DONTU + ") REFERENCES " + TABLE_LOAIDONTU + "(" + COLUMN_MALOAI_DONTU + "), "
            + "FOREIGN KEY (" + COLUMN_EMAIL_NV_DONTU + ") REFERENCES " + TABLE_NHANVIEN + "(" + COLUMN_EMAIL_NV + "))";

    private static final String CREATE_TABLE_CHAMCONG = "CREATE TABLE " + TABLE_CHAMCONG + " ("
            + COLUMN_MACHAMCONG + " VARCHAR(10) PRIMARY KEY, "
            + COLUMN_THOIGIANTAO + " DATETIME, "
            + COLUMN_LOAICHAMCONG + " VARCHAR(50), "
            + COLUMN_THOIGIANTRE + " VARCHAR(10), "
            + COLUMN_MANHANVIEN + " VARCHAR(10), "
            + COLUMN_MACA_CHAMCONG + " VARCHAR(10), "
            + "FOREIGN KEY (" + COLUMN_MANHANVIEN + ") REFERENCES " + TABLE_NHANVIEN + "(" + COLUMN_MANHANVIEN + "), "
            + "FOREIGN KEY (" + COLUMN_MACA_CHAMCONG + ") REFERENCES " + TABLE_CACHAMCONG + "(" + COLUMN_MACA + "))";
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        //Xóa bảng nếu tồn tại
        onCreate(db);
    }

    // Insert into NhanVien
    public long insertNhanVien(String maNV, String tenNV, String sdt, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MANHANVIEN, maNV);
        values.put(COLUMN_TENNHANVIEN, tenNV);
        values.put(COLUMN_SDT, sdt);
        values.put(COLUMN_EMAIL_NV, email);

        return db.insert(TABLE_NHANVIEN, null, values);
    }

    // Insert into LoaiDonTu
    public long insertLoaiDonTu(String maLoai, String tenLoai) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MALOAI_DONTU, maLoai);
        values.put(COLUMN_TENLOAIDONTU, tenLoai);

        return db.insert(TABLE_LOAIDONTU, null, values);
    }

    // Insert into CaChamCong
    public long insertCaChamCong(String maCa, String tenCa, String thoiGianBatDau, String thoiGianKetThuc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MACA, maCa);
        values.put(COLUMN_TENCACHA, tenCa);
        values.put(COLUMN_THOIGIANBATDAU, thoiGianBatDau);
        values.put(COLUMN_THOIGIANKETTHUC, thoiGianKetThuc);

        return db.insert(TABLE_CACHAMCONG, null, values);
    }

    // Insert into TaiKhoan
    public long insertTaiKhoan(String email, String matKhau, String hoTen) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMAIL_NV_DONTU, email);
        values.put(COLUMN_MATKHAU, matKhau);
        values.put(COLUMN_HOTEN, hoTen);

        return db.insert(TABLE_TAIKHOAN, null, values);
    }

    // Insert into DonTu
    public long insertDonTu(String maDonTu, String thoiGianTao, String trangThai, String maLoaiDonTu, String email, String phanQuyen) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MADONTU, maDonTu);
        values.put(COLUMN_THOIGIANTAO, thoiGianTao);
        values.put(COLUMN_TRANGTHAI, trangThai);
        values.put(COLUMN_MALOAI_DONTU, maLoaiDonTu);
        values.put(COLUMN_EMAIL_NV_DONTU, email);
        values.put(COLUMN_PHANQUYEN, phanQuyen);

        return db.insert(TABLE_DONTU, null, values);
    }

    // Insert into ChamCong
    public long insertChamCong(String maChamCong, String loaiChamCong, String thoiGianTre, String maNhanVien, String maCa) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MACHAMCONG, maChamCong);
        values.put(COLUMN_LOAICHAMCONG, loaiChamCong);
        values.put(COLUMN_THOIGIANTRE, thoiGianTre);
        values.put(COLUMN_MANHANVIEN, maNhanVien);
        values.put(COLUMN_MACA_CHAMCONG, maCa);

        return db.insert(TABLE_CHAMCONG, null, values);
    }

    public String loadDataHandler(String TABLE_NAME) {
        String result = "";
        String query = "SELECT* FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " + result_1 +
                    System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
}