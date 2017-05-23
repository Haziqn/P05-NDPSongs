package com.example.a15017523.p05_ndpsongs;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.Serializable;
import java.util.ArrayList;

import static android.R.attr.data;

/**
 * Created by 15031795 on 23/5/2017.
 */

public class SecondActivity extends AppCompatActivity {

    ListView lv;
    Button btnShow;
    ArrayList<Song> al;
    ArrayAdapter aa;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO implement the Custom ListView
        setContentView(R.layout.second_activity);

        btnShow = (Button) findViewById(R.id.btnShow);

        lv = (ListView) findViewById(R.id.lv);
        DBHelper db = new DBHelper(SecondActivity.this);
        al = db.getAllSong();

        aa = new ArrayAdapter(this, R.layout.row, al);
        lv.setAdapter(aa);
        aa.notifyDataSetChanged();
        db.close();

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                al.clear();
                al.addAll(dbh.get5Songs());
                dbh.close();
                lv.setAdapter(aa);
                aa.notifyDataSetChanged();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {

                Intent i = new Intent(SecondActivity.this,
                        ThirdActivity.class);
                Song data = al.get(position);

//           int id = data.get_id();
//           String title = data.getTitle();
//           String singer = data.getSingers();
//           int years = data.getYear();
//           int stars = data.getStars();

//           Song target = new Song(title,singer,years,stars);
                i.putExtra("data", data);
                startActivityForResult(i, 9);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9) {
            DBHelper db = new DBHelper(SecondActivity.this);
            al = db.getAllSong();
            aa = new ArrayAdapter(SecondActivity.this, R.layout.row, al);
            lv.setAdapter(aa);
        }
    }
}