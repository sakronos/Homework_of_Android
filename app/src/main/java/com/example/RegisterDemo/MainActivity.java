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
                book.setName("Java程序设计");
                book.setAuthor("清华");
                book.setPages(650);
                book.setPrice(9.5);
                book.setPress("清华");
                book.save();
                Book book1 = new Book();
                book1.setName("C语言程序设计");
                book1.setAuthor("清华");
                book1.setPages(450);
                book1.setPrice(5);
                book1.setPress("清华");
                book1.save();
            }
        });


        Button updateData = findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book = new Book();
                //book.setName("The Lost Symbol");
                //book.setAuthor("Dan Brown");

                book.setPrice(20.95);
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
                    text.append("Name:").append(book.getName()).append(" ").append("Author:").append(book.getAuthor()).append(" ").append("Pages:").append(book.getPages()).append("\n").append("Price:").append(book.getPrice()).append(" ").append("Press:").append(book.getPress()).append("\n\n");
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
                        .limit(2)
                        .offset(2)       //偏移量
                        .find(Book.class);
                for (Book book:books){
                    text.append("Name:").append(book.getName())
                            .append(" ").append("Author:").append(book.getAuthor())
                            .append(" ").append("Pages:").append(book.getPages()).append("\n\n");
                }
                textView.setText(text.toString());
            }
        });

    }


}
