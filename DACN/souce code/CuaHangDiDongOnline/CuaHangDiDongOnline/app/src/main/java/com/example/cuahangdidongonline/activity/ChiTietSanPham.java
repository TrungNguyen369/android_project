package com.example.cuahangdidongonline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.cuahangdidongonline.R;
import com.example.cuahangdidongonline.model.Giohang;
import com.example.cuahangdidongonline.model.Sanpham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;

public class ChiTietSanPham extends AppCompatActivity {

    Toolbar toolbarChitiet;
    ImageView imgChitiet;
    TextView txtTen,    txtGia, txtMota;
    Spinner spinner;
    Button btnDatmua;
    int id = 0;
    String TenChitiet = "";
    int GiaChiTtiet = 0;
    String HinhanhChitiet = "";
    String MotaChitiet = "";
    int Idsanpham = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_san_pham);
        AnhXa();
        ActionToolBar();
        GetInformation();
        CatchEventSpinner();
        EventButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent = new Intent(getApplicationContext(), com.example.cuahangdidongonline.activity.Giohang.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    private void GetInformation() {

        Sanpham sanpham = (Sanpham) getIntent().getSerializableExtra("thongtinsanpham");
        id = sanpham.getID();
        TenChitiet = sanpham.getTensanpham();
        GiaChiTtiet = sanpham.getGiasanpham();
        HinhanhChitiet = sanpham.getHinhanhsanpham();
        MotaChitiet = sanpham.getMotasanpham();
        Idsanpham = sanpham.getIDsanpham();
        txtTen.setText(TenChitiet);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGia.setText("Giá: "+ decimalFormat.format(GiaChiTtiet)+ "Đ");
        txtMota.setText(MotaChitiet);
        Picasso.with(getApplicationContext()).load(HinhanhChitiet)
                .placeholder(R.drawable.noimage)
                .error(R.drawable.error)
                .into(imgChitiet);
    }

    private void EventButton() {
        btnDatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MainActivity.manggiohang.size() > 0){
                    int sl = Integer.parseInt (spinner.getSelectedItem().toString());
                    boolean exists = false;
                    for (int i = 0; i < MainActivity.manggiohang.size(); i++){
                        if (MainActivity.manggiohang.get(i).getIdsp() == id){
                            MainActivity.manggiohang.get(i).setSoluongsp(MainActivity.manggiohang.get(i).getSoluongsp() + sl);
                            if (MainActivity.manggiohang.get(i).getSoluongsp() >= 10){
                                MainActivity.manggiohang.get(i).setSoluongsp(10);
                            }
                            MainActivity.manggiohang.get(i).setGiasp(GiaChiTtiet * MainActivity.manggiohang.get(i).getSoluongsp());
                            exists = true;
                        }
                    }
                    if (exists == false){
                        int soluong = Integer.parseInt (spinner.getSelectedItem().toString());
                        long Giamoi = soluong*GiaChiTtiet;
                        MainActivity.manggiohang.add(new Giohang(id,TenChitiet,Giamoi,HinhanhChitiet,soluong));
                    }
                }else {
                    int soluong = Integer.parseInt (spinner.getSelectedItem().toString());
                    long Giamoi = soluong*GiaChiTtiet;
                    MainActivity.manggiohang.add(new Giohang(id, TenChitiet, Giamoi, HinhanhChitiet, soluong));
                }
                Intent intent = new Intent(getApplicationContext(), com.example.cuahangdidongonline.activity.Giohang.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }



    private void ActionToolBar() {
        setSupportActionBar(toolbarChitiet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarChitiet.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void AnhXa() {
        toolbarChitiet = findViewById(R.id.toolbarchitietsanpham);
        imgChitiet = findViewById(R.id.imageviewchitietsanpham);
        txtTen = findViewById(R.id.textviewtenchitietsanpham);
        txtGia = findViewById(R.id.textviewggiachitietsanpham);
        txtMota = findViewById(R.id.textkviewmotachitietsanpham);
        spinner = findViewById(R.id.spinner);
        btnDatmua = findViewById(R.id.buttondatmua);
    }
}