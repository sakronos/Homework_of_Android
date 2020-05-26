package com.example.RegisterDemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;


import org.litepal.LitePal;

import org.litepal.tablemanager.Connector;

import java.util.List;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase = findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Connector.getDatabase();
                Toast.makeText(getApplicationContext(),"Database Successfully Created",Toast.LENGTH_SHORT).show();
            }
        });

        Button addData = findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Book book = new Book();
                book.setName("线代");
                book.setAuthor("同济");
                book.setPages(200);
                book.setPrice(4);
                book.setPress("高等教育");
                book.save();
            }
        });


        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                //book.setName("The Lost Symbol");
                //book.setAuthor("Dan Brown");

                book.setPrice(19.95);
                book.setPress("Anchor");
                book.updateAll("name=? and author=?","The Da Vinci Code","Dan Brown");

            }
        });

        Button deleteALLData = findViewById(R.id.delete_data);
        deleteALLData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Book.class,"price >?","15");
            }
        });

        Button queryButton = findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = findViewById(R.id.QueryResult);
                StringBuilder text = new StringBuilder();
                List<Book> books = LitePal.findAll(Book.class);
                for (Book book:books){
                    text.append("Name:").append(book.getName()).append("\n").append("Author:").append(book.getAuthor()).append("\n").append("Pages:").append(book.getPages()).append("\n").append("Price:").append(book.getPrice()).append("\n").append("Press:").append(book.getPress()).append("\n\n");
                }
                textView.setText(text.toString());
            }
        });

        Button fluentQuery = findViewById(R.id.fluent_query);
        fluentQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView = findViewById(R.id.QueryResult);
                StringBuilder text = new StringBuilder();
                List<Book> books = LitePal.select("name","author","pages")
                        .where("pages >?","400")
                        .order("pages")
                        .limit(10)
                        //.offset(10)       //偏移量
                        .find(Book.class);
                for (Book book:books){
                    text.append("Name:").append(book.getName())
                            .append("\n").append("Author:").append(book.getAuthor())
                            .append("\n").append("Pages:").append(book.getPages()).append("\n\n");
                }
                textView.setText(text.toString());
            }
        });

    }


}
