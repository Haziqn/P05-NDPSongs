package com.example.a15017523.p05_ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ThirdActivity extends AppCompatActivity {

    Button buttonCancel, buttonDelete, buttonUpdate;
    EditText editTextID, editTextSinger, editTextTitle, editTextYear;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    RadioGroup rg;

    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_activity);

        editTextID = (EditText) findViewById(R.id.etId);
        editTextTitle = (EditText) findViewById(R.id.etTitle);
        editTextSinger = (EditText) findViewById(R.id.etSingers);
        editTextYear = (EditText) findViewById(R.id.etYear);

        rb1 = (RadioButton) findViewById(R.id.radio1);
        rb2 = (RadioButton) findViewById(R.id.radio2);
        rb3 = (RadioButton) findViewById(R.id.radio3);
        rb4 = (RadioButton) findViewById(R.id.radio4);
        rb5 = (RadioButton) findViewById(R.id.radio5);
        rg = (RadioGroup) findViewById(R.id.radioGroup);

        buttonCancel = (Button) findViewById(R.id.btnCancel);
        buttonDelete = (Button) findViewById(R.id.btnDelete);
        buttonUpdate = (Button) findViewById(R.id.btnUpdate);

        dbHelper = new DBHelper(this);

        final Song currentSong = (Song) getIntent().getSerializableExtra("song");

        editTextID.setText(currentSong.getId());
        editTextTitle.setText(currentSong.getTitle());
        editTextSinger.setText(currentSong.getSingers());
        editTextYear.setText(currentSong.getYear());


        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = editTextTitle.getText().toString().trim();
                String singer = editTextSinger.getText().toString().trim();
                String year = editTextYear.getText().toString().trim();
                int id = rg.getCheckedRadioButtonId();
                currentSong.setTitle(title);
                currentSong.setSingers(singer);
                try {
                    currentSong.setYear(Integer.parseInt(year));
                    currentSong.setStars(id);
                    if (dbHelper.updateSong(currentSong) > 0) {
                        Toast.makeText(ThirdActivity.this, "Song updated", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent();
                        ThirdActivity.this.setResult(-1);
                        ThirdActivity.this.finish();
                        return;
                    }
                    Toast.makeText(ThirdActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(ThirdActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
