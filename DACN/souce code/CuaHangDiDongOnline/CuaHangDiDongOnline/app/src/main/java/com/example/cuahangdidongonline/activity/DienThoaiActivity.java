package com.example.cuahangdidongonline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangdidongonline.R;
import com.example.cuahangdidongonline.adapter.DienthoaiAdapter;
import com.example.cuahangdidongonline.model.Sanpham;
import com.example.cuahangdidongonline.ultil.CheckConnection;
import com.example.cuahangdidongonline.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DienThoaiActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbardt;
    ListView lvdt;
    DienthoaiAdapter dienthoaiAdapter;
    ArrayList<Sanpham> mangdt;
    int iddt = 0;
    int page = 1;
    View fooderview;
    boolean isLoading = false;
    mHamdler hamdler;
    boolean limitadata = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dien_thoai);
        AnhXa();
        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            GetIdloaisp();
            ActionToolBAr();
            GetDaTa(page);
            LoadMoreData();
        }else {
            CheckConnection.showToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối mạng");
            finish();
        }

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

    private void LoadMoreData() {
        lvdt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),ChiTietSanPham.class);
                intent.putExtra("thongtinsanpham",mangdt.get(i));
                startActivity(intent);
            }
        });
        lvdt.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int FristItem, int VisibleItem, int TotalItem) {
                if (FristItem + VisibleItem == TotalItem && TotalItem != 0 && isLoading == false && limitadata == false){
                    isLoading = true;
                    Thread threadData = new ThreadData();
                    threadData.start();
                }
            }
        });
    }

    private void GetDaTa(int Page) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String duongdan = Server.Duongdandienthoai+ String.valueOf(Page);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                int id = 0;
                String Tendt = "";
                int Giadt = 0;
                String Hinhanhdt = "";
                String Mota = "";
                int idsanpham = 0;
                if (response != null && response.length() != 2){
                    lvdt.removeFooterView(fooderview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++ ){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            Tendt = jsonObject.getString("tensp");
                            Giadt = jsonObject.getInt("giasp");
                            Hinhanhdt = jsonObject.getString("hinhanhsp");
                            Mota = jsonObject.getString("motasp");
                            idsanpham = jsonObject.getInt("idsanpham");
                            mangdt.add(new Sanpham(id,Tendt,Giadt,Hinhanhdt,Mota,idsanpham));
                            dienthoaiAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    limitadata = true;
                    lvdt.removeFooterView(fooderview);
                    CheckConnection.showToast_Short(getApplicationContext(),"Đã hết dữ liệu");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param = new HashMap<String, String>();
                param.put("idsanpham",String.valueOf(iddt));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void ActionToolBAr() {
        setSupportActionBar(toolbardt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbardt.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void GetIdloaisp() {
        iddt = getIntent().getIntExtra("idloaisanpham",-1);
        Log.d("Giá trị loại sản phẩm",iddt+"");
    }

    private void AnhXa() {
        toolbardt = (Toolbar) findViewById(R.id.toolbardienthoai);
        lvdt = (ListView) findViewById(R.id.listviewdienthoai);
        mangdt = new ArrayList<>();
        dienthoaiAdapter = new DienthoaiAdapter(getApplicationContext(),mangdt);
        lvdt.setAdapter(dienthoaiAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        fooderview = inflater.inflate(R.layout.progressbar, null);
        hamdler = new mHamdler();
    }
    public class mHamdler extends Handler{
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    lvdt.addFooterView(fooderview);
                    break;
                case 1:
                    GetDaTa(++page);
                    isLoading = false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread{
        @Override
        public void run() {
            hamdler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message = hamdler.obtainMessage(1);
            hamdler.sendMessage(message);
            super.run();
        }
    }
}