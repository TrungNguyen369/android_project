package com.example.cuahangdidongonline.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cuahangdidongonline.R;
import com.example.cuahangdidongonline.adapter.DienthoaiAdapter;
import com.example.cuahangdidongonline.adapter.LaptopAdapter;
import com.example.cuahangdidongonline.model.Sanpham;
import com.example.cuahangdidongonline.ultil.CheckConnection;
import com.example.cuahangdidongonline.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class LapTopActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbarlaptop;
    ListView lvlaptop;
    LaptopAdapter laptopAdapter;
    ArrayList<Sanpham> manglaptop;
    int idlaptop = 0;
    int page = 1;
    View fooderview;
    boolean isLoading = false;
    mHamdler hamdler;
    boolean limitadata = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lap_top);

        if (CheckConnection.haveNetworkConnection(getApplicationContext())){
            AnhXa();
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
        lvlaptop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(),ChiTietSanPham.class);
                intent.putExtra("thongtinsanpham",manglaptop.get(i));
                startActivity(intent);
            }
        });
        lvlaptop.setOnScrollListener(new AbsListView.OnScrollListener() {
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
    private void AnhXa() {
        toolbarlaptop = (Toolbar) findViewById(R.id.toolbarlaptop);
        lvlaptop = (ListView) findViewById(R.id.listviewlaptop);
        manglaptop = new ArrayList<>();
        laptopAdapter = new LaptopAdapter(getApplicationContext(),manglaptop);
        lvlaptop.setAdapter(laptopAdapter);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        fooderview = inflater.inflate(R.layout.progressbar, null);
        hamdler = new mHamdler();
    }

    private void GetIdloaisp() {
        idlaptop = getIntent().getIntExtra("idloaisanpham",-1);
        Log.d("Giá trị loại sản phẩm",idlaptop+"");
    }
    private void ActionToolBAr() {
        setSupportActionBar(toolbarlaptop);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarlaptop.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
                String Tenlaptop = "";
                int Gialaptop = 0;
                String Hinhanhlaptop = "";
                String Motalaptop = "";
                int idsplaptop = 0;
                if (response != null && response.length() != 2){
                    lvlaptop.removeFooterView(fooderview);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++ ){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            id = jsonObject.getInt("id");
                            Tenlaptop = jsonObject.getString("tensp");
                            Gialaptop = jsonObject.getInt("giasp");
                            Hinhanhlaptop = jsonObject.getString("hinhanhsp");
                            Motalaptop = jsonObject.getString("motasp");
                            idsplaptop = jsonObject.getInt("idsanpham");
                            manglaptop.add(new Sanpham(id,Tenlaptop,Gialaptop,Hinhanhlaptop,Motalaptop,idsplaptop));
                            laptopAdapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    limitadata = true;
                    lvlaptop.removeFooterView(fooderview);
                    CheckConnection.showToast_Short(getApplicationContext(), "Đã hết dữ liệu");
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
                param.put("idsanpham",String.valueOf(idlaptop));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }
    public class mHamdler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case 0:
                    lvlaptop.addFooterView(fooderview);
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