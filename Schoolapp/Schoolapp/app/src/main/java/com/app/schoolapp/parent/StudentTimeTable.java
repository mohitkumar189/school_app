package com.app.schoolapp.parent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.app.schoolapp.R;
import com.app.schoolapp.adapter.AdapterStudentTimeTable;
import com.app.schoolapp.interfaces.ApiResponse;
import com.app.schoolapp.interfaces.OnCustomItemClicListener;
import com.app.schoolapp.model.ModelTimeTable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class StudentTimeTable extends AppCompatActivity implements OnCustomItemClicListener, ApiResponse {

    private Toolbar toolbar;
    private Context context;
    private BroadcastReceiver broadcastReceiver;
    private RecyclerView mRecyclerView;
    private ModelTimeTable model;
    private ArrayList<ModelTimeTable> timetableList;
    private ArrayList<String> periodName;
    private ArrayList<String> daysList;
    private LinearLayoutManager layoutManager;
    private AdapterStudentTimeTable adapterTimeTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_table);
        context = this;
        init();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //  gettimetableList();
        setListener();

        model = new ModelTimeTable();
        model.setTeacher_name("");
        timetableList.add(model);

        periodName.add("1");
        periodName.add("2");
        periodName.add("3");
        periodName.add("4");
        periodName.add("5");
        periodName.add("6");
        periodName.add("7");

        adapterTimeTable = new AdapterStudentTimeTable(context, this, timetableList, periodName, daysList);
        mRecyclerView.setAdapter(adapterTimeTable);

    }

    private void init() {
        timetableList = new ArrayList<>();
        periodName = new ArrayList<>();
        daysList = new ArrayList<>();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.package.ACTION_LOGOUT");

        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("onReceive", "Logout in progress");
                //At this point you should start the login activity and finish this one
                finish();
            }
        };
        registerReceiver(broadcastReceiver, intentFilter);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(context);

        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setLayoutManager(layoutManager);

    }


    public void setListener() {

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });

    }

   /* public void gettimetableList() {

        if (AppUtils.isNetworkAvailable(context)) {

            HashMap<String, Object> hm = new HashMap<>();

            hm.put("teacherid", AppUtils.getUserId(context));
            hm.put("authkey", Constant.AUTHKEY);
            hm.put("schoolid", AppUtils.getSchoolId(context));
            //hm.put("studentid", "70");

            String url = getResources().getString(R.string.base_url) + getResources().getString(R.string.teacher_timetable);
            new CommonAsyncTaskHashmap(1, context, this).getquery(url, hm);

        } else {
            Toast.makeText(context, context.getResources().getString(R.string.message_network_problem), Toast.LENGTH_SHORT).show();
        }

    }*/


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);

    }


    @Override
    public void onItemClickListener(int position, int flag) {

    }

    @Override
    public void onPostSuccess(int method, JSONObject response) {
        try {
            if (method == 1) {
                if (response.getString("response").equalsIgnoreCase("1")) {

                    periodName.clear();
                    timetableList.clear();

                    //   JSONObject result = response.getJSONObject("result");
                    JSONArray Period = response.getJSONArray("result");

                    for (int i = 0; i < Period.length(); i++) {

                        JSONObject ob = Period.getJSONObject(i);

                        periodName.add(ob.getString("PeriodName"));
                        daysList.add(ob.getJSONArray("days").toString());

                        JSONArray days = ob.getJSONArray("days");
                        for (int j = 0; j < days.length(); j++) {

                            JSONObject jo = days.getJSONObject(j);
                            model = new ModelTimeTable();

                            model.setDaysArray(jo.toString());
                            model.setDay(jo.getString("day"));
                            model.setClassname(jo.getString("class"));
                            model.setSubject(jo.getString("subject"));
                            model.setTime(jo.getString("time"));
                            model.setTeacher_name(jo.getString("teacher_name"));

                            timetableList.add(model);
                        }
                    }
                    adapterTimeTable = new AdapterStudentTimeTable(context, this, timetableList, periodName, daysList);
                    mRecyclerView.setAdapter(adapterTimeTable);

                } else {

                    Toast.makeText(context, response.getString("msg"), Toast.LENGTH_SHORT).show();
                }


            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPostFail(int method, String response) {

    }
}
