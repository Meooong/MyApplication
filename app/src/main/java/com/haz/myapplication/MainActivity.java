package com.haz.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView list;
    list adapter;
    DBController query;
    List<adaptervb> daftardetail = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databasehelper include = new databasehelper(this);
        try {
            include.createDataBase();
            System.out.println("Siiiiip");
        } catch (Exception e) {

            e.printStackTrace();
            // TODO: handle exception
        }



        query = new DBController(this);
        list = (ListView) findViewById(R.id.listview);
        daftardetail = this.query.get_pel();
        adapter = new list(this, daftardetail);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView txtnama=(TextView) view.findViewById(R.id.textView2);
                TextView txtket=(TextView) view.findViewById(R.id.textView3);
                TextView txturl_image=(TextView) view.findViewById(R.id.textView6);

                Intent intent = new Intent(MainActivity.this,detail.class);
                intent.putExtra("nama",txtnama.getText().toString());
                intent.putExtra("ket",txtket.getText().toString());
                intent.putExtra("url",txturl_image.getText().toString());
                startActivity(intent);
            }
        });

    }

}
