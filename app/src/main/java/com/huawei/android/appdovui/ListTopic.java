package com.huawei.android.appdovui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class ListTopic extends AppCompatActivity {

    ListView listView;
    ArrayList<Topic> arrayList;
    AdapterTopic adapterTopic;
    Toolbar thanhmenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_topic);

        setSupportActionBar(thanhmenu);//ho tro actionbar bang toolbar

        anhxa();

        arrayList=new ArrayList<>();
        arrayList.add(new Topic("Random", R.drawable.dice));
        arrayList.add(new Topic("Toán", R.drawable.math));
        arrayList.add(new Topic("Văn", R.drawable.nen_van));
        arrayList.add(new Topic("Anh", R.drawable.nen_anh));
        arrayList.add(new Topic("Hoá", R.drawable.nen_hoa));

        adapterTopic=new AdapterTopic(ListTopic.this, R.layout.activity_iltem_topic, arrayList);
        listView.setAdapter(adapterTopic);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Toast.makeText(ListTopic.this, "Bạn đã chọn chủ đề Random", Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(ListTopic.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    showDialog();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menutoolbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { //chưa chạy được
        switch (item.getItemId()){
            case R.id.helo:
                Toast.makeText(this, "Chào bạn !", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.vote:
                Toast.makeText(this, "Đánh giá", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.about:
                Toast.makeText(this, "About this game", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.search:
                Toast.makeText(this, "Tìm kiếm", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.back:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage("Đang cập nhật !");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }

    private void anhxa() {
        listView=findViewById(R.id.list);
        thanhmenu=findViewById(R.id.toolbar);

    }

}