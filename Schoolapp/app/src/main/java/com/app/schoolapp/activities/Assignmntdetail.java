package com.app.schoolapp.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.app.schoolapp.R;

public class Assignmntdetail extends AppCompatActivity {


    private Context context;
    private RecyclerView recycler_list;
    private Toolbar toolbar;
    private TextView text_attachment, text_submissiondate, text_date, text_description, text_subject, text_class, text_name, text_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignmnt_detail);
        context = this;
        init();
        setListener();
    }

    private void setListener() {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recycler_list = (RecyclerView) findViewById(R.id.recycler_list);
        recycler_list.setLayoutManager(new GridLayoutManager(context, 3));

        text_attachment = (TextView) findViewById(R.id.text_attachment);
        text_submissiondate = (TextView) findViewById(R.id.text_submissiondate);
        text_date = (TextView) findViewById(R.id.text_date);
        text_description = (TextView) findViewById(R.id.text_description);
        text_subject = (TextView) findViewById(R.id.text_subject);
        text_class = (TextView) findViewById(R.id.text_class);
        text_name = (TextView) findViewById(R.id.text_name);
        text_title = (TextView) findViewById(R.id.text_title);
    }

}
