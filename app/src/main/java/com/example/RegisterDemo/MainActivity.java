package com.example.RegisterDemo;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText contact_name;
    private EditText phone_number;
    private TextView contact_content;
    private MyHelper myHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contact_name = (EditText)findViewById(R.id.contact_name);
        phone_number = (EditText)findViewById(R.id.phone_number);
        contact_content = (TextView)findViewById(R.id.contact_content);
        findViewById(R.id.AddRecord).setOnClickListener(this);
        findViewById(R.id.QueryRecord).setOnClickListener(this);
        findViewById(R.id.ModifyRecord).setOnClickListener(this);
        findViewById(R.id.DeleteRecord).setOnClickListener(this);
        myHelper = new MyHelper(this);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        String name;
        String phone;
        SQLiteDatabase db;
        ContentValues values;

        switch (v.getId()){
            case R.id.AddRecord:
                name = contact_name.getText().toString().trim();
                phone = phone_number.getText().toString().trim();
                db = myHelper.getWritableDatabase();
                values = new ContentValues();
                values.put("name",name);
                values.put("phone",phone);
                db.insert("information",null,values);
                Toast.makeText(this,getResources().getString(R.string.AddSuccessToast)+name,Toast.LENGTH_SHORT).show();
                db.close();
                break;
            case R.id.QueryRecord:
                db = myHelper.getReadableDatabase();
                Cursor cursor = db.query("information",null,null,null,null,null,null);
                if (cursor.getCount()==0){
                    contact_content.setText("");
                    Toast.makeText(this,R.string.NoRecordToast,Toast.LENGTH_SHORT).show();
                }else {
                    cursor.moveToFirst();
                    contact_content.setText("NAME: "+cursor.getString(1)+" TEL: "+cursor.getString(2)+"\n");
                }
                while (cursor.moveToNext()){
                    contact_content.append("NAME: "+cursor.getString(1)+" TEL: "+cursor.getString(2)+"\n");
                }
                cursor.close();
                db.close();
                break;
            case R.id.ModifyRecord:
                db = myHelper.getWritableDatabase();
                values = new ContentValues();
                values.put("phone",phone_number.getText().toString().trim());
                db.update("information",values,"name=?",new String[]{contact_name.getText().toString().trim()});
                Toast.makeText(this,R.string.UpdateSuccessToast,Toast.LENGTH_SHORT).show();
                db.close();
                break;
            case R.id.DeleteRecord:
                db = myHelper.getWritableDatabase();
                db.delete("information","name=?",new String[]{contact_name.getText().toString().trim()});
                Toast.makeText(this,R.string.DeleteSuccessToast,Toast.LENGTH_SHORT).show();
                db.close();
                break;
        }
    }
}
