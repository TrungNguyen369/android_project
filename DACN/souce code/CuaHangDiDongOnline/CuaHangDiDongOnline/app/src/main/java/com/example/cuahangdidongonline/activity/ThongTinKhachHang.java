package com.example.cuahangdidongonline.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangdidongonline.R;
import com.example.cuahangdidongonline.ultil.CheckConnection;
import com.example.cuahangdidongonline.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachHang extends AppCompatActivity {

    EditText edtTenKhachHang , edtSDT, edtEmail;
    Button btnXacNhan, btnQuayLai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        AnhXa();
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
                EventButton();
        }else {
            CheckConnection.showToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
        }
    }

    private void EventButton() {
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String ten = edtTenKhachHang.getText().toString().trim();
                final String sdt = edtSDT.getText().toString().trim();
                final String email = edtEmail.getText().toString().trim();
                if (ten.length()>0 && sdt.length()>0 && email.length()>0){
                    final RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.Duongdandonhang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("madonhang" , madonhang);
                            if (Integer.parseInt(madonhang)>0){
                                RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
                                StringRequest request = new StringRequest(Request.Method.POST, Server.Duongdanchitietdonhang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        if (response.equals("1")){
                                            MainActivity.manggiohang.clear();
                                            CheckConnection.showToast_Short(getApplicationContext(),"Bạn đã đặt hàng thành công");
                                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                            startActivity(intent);
                                            CheckConnection.showToast_Short(getApplicationContext(),"Mời bạn tiếp tục đặt sản phẩm");

                                        }else {
                                            CheckConnection.showToast_Short(getApplicationContext(),"Bạn đặt hàng không thành công, vui lòng thử lại");

                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray = new JSONArray();
                                        for (int i = 0; i < MainActivity.manggiohang.size(); i++){
                                            JSONObject jsonObject = new JSONObject();
                                            try {
                                                jsonObject.put("madonhang",madonhang);
                                                jsonObject.put("masanpham",MainActivity.manggiohang.get(i).getIdsp());
                                                jsonObject.put("tensanpham",MainActivity.manggiohang.get(i).getTensp());
                                                jsonObject.put("giasanpham",MainActivity.manggiohang.get(i).getGiasp());
                                                jsonObject.put("soluongsanpham",MainActivity.manggiohang.get(i).getSoluongsp());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                                jsonArray.put(jsonObject);
                                        }
                                        HashMap<String, String> hashMap = new HashMap<String, String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(request);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String, String> hashMap = new HashMap<String, String>();
                            hashMap.put("tenkhachhang" , ten);
                            hashMap.put("sodienthoai", sdt);
                            hashMap.put("email" , email);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else {
                    CheckConnection.showToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại đữ liệu");
                }
            }
        });
    }

    private void AnhXa() {
        edtEmail = findViewById(R.id.edittextEmailKhachHang);
        edtSDT = findViewById(R.id.edittextSDTKhachHang);
        edtTenKhachHang = findViewById(R.id.edittextTenKhachHang);
        btnXacNhan = findViewById(R.id.buttonXacNhan);
        btnQuayLai = findViewById(R.id.buttonTroVe);
    }
}