package com.app.schoolapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.schoolapp.R;
import com.app.schoolapp.interfaces.OnCustomItemClicListener;
import com.app.schoolapp.model.ModelTimeTable;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by admin on 26-11-2015.
 */
public class AdapterStudentTimeTable extends RecyclerView.Adapter<AdapterStudentTimeTable.CustomViewHolder> {
    ArrayList<ModelTimeTable> detail;
    Context mContext;
    OnCustomItemClicListener listener;
    int pos = 0;
    private ModelTimeTable model;
    ArrayList<String> periodName;
    ArrayList<String> daysList;

    public AdapterStudentTimeTable(Context context, OnCustomItemClicListener lis, ArrayList<ModelTimeTable> list, ArrayList<String> periodName, ArrayList<String> daysList) {

        this.mContext = context;
        this.listener = lis;
        this.periodName = periodName;
        this.daysList = daysList;

    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_timetable, viewGroup, false);

        final CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;

    }


    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {

        holder.text_periodname.setText(periodName.get(position));

       // setDaysData(holder, position);


    }

    private void setDaysData(CustomViewHolder holder, int position) {
        try {
            detail = new ArrayList<>();
            String daysdata = daysList.get(position);

            JSONArray days = new JSONArray(daysdata);
            for (int j = 0; j < days.length(); j++) {

                JSONObject jo = days.getJSONObject(j);
                model = new ModelTimeTable();

                model.setDaysArray(jo.toString());
                model.setDay(jo.getString("day"));
                model.setClassname(jo.getString("class"));
                model.setSubject(jo.getString("subject"));
                model.setTime(jo.getString("time"));
                model.setTeacher_name(jo.getString("teacher_name"));

                detail.add(model);

            }
            for (int i = 0; i < detail.size(); i++) {


                if (detail.get(i).getDay().equalsIgnoreCase("mon")) {


                    holder.text_class_mon.setText(detail.get(i).getClassname());
                    holder.text_subject_mon.setText(detail.get(i).getSubject());
                    holder.text_time_mon.setText(detail.get(i).getTime());
                    if (detail.get(i).getSubject().equalsIgnoreCase("")) {
                        holder.text_subject_mon.setText("-");
                    }

                } else if (detail.get(i).getDay().equalsIgnoreCase("tue")) {

                    holder.text_class_tue.setText(detail.get(i).getClassname());
                    holder.text_subject_tue.setText(detail.get(i).getSubject());
                    holder.text_time_tue.setText(detail.get(i).getTime());
                    if (detail.get(i).getSubject().equalsIgnoreCase("")) {
                        holder.text_subject_tue.setText("-");
                    }
                } else if (detail.get(i).getDay().equalsIgnoreCase("wed")) {

                    holder.text_class_wed.setText(detail.get(i).getClassname());
                    holder.text_subject_wed.setText(detail.get(i).getSubject());
                    holder.text_time_wed.setText(detail.get(i).getTime());
                    if (detail.get(i).getSubject().equalsIgnoreCase("")) {
                        holder.text_subject_wed.setText("-");
                    }
                } else if (detail.get(i).getDay().equalsIgnoreCase("thu")) {

                    holder.text_class_thur.setText(detail.get(i).getClassname());
                    holder.text_subject_thur.setText(detail.get(i).getSubject());
                    holder.text_time_thur.setText(detail.get(i).getTime());
                    if (detail.get(i).getSubject().equalsIgnoreCase("")) {
                        holder.text_subject_thur.setText("-");
                    }
                } else if (detail.get(i).getDay().equalsIgnoreCase("fri")) {

                    holder.text_class_fri.setText(detail.get(i).getClassname());
                    holder.text_subject_fri.setText(detail.get(i).getSubject());
                    holder.text_time_fri.setText(detail.get(i).getTime());
                    if (detail.get(i).getSubject().equalsIgnoreCase("")) {
                        holder.text_subject_fri.setText("-");
                    }
                } else if (detail.get(i).getDay().equalsIgnoreCase("sat")) {

                    holder.text_class_sat.setText(detail.get(i).getClassname());
                    holder.text_subject_sat.setText(detail.get(i).getSubject());
                    holder.text_time_sat.setText(detail.get(i).getTime());
                    if (detail.get(i).getSubject().equalsIgnoreCase("")) {
                        holder.text_subject_sat.setText("-");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public int getItemCount() {
        return periodName.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView text_periodname, text_subject_mon, text_class_mon, text_time_mon, text_subject_tue, text_class_tue, text_time_tue,
                text_subject_wed, text_class_wed, text_time_wed, text_subject_thur, text_class_thur, text_time_thur, text_subject_fri,
                text_class_fri, text_time_fri, text_subject_sat, text_class_sat, text_time_sat;

        public CustomViewHolder(View view) {
            super(view);

            this.text_periodname = (TextView) view
                    .findViewById(R.id.text_periodname);
            this.text_subject_mon = (TextView) view.findViewById(R.id.text_subject_mon);
            this.text_class_mon = (TextView) view
                    .findViewById(R.id.text_class_mon);
            this.text_time_mon = (TextView) view.findViewById(R.id.text_time_mon);
            this.text_subject_tue = (TextView) view.findViewById(R.id.text_subject_tue);
            this.text_class_tue = (TextView) view.findViewById(R.id.text_class_tue);
            this.text_time_tue = (TextView) view.findViewById(R.id.text_time_tue);
            this.text_subject_wed = (TextView) view.findViewById(R.id.text_subject_wed);
            this.text_class_wed = (TextView) view.findViewById(R.id.text_class_wed);

            this.text_time_wed = (TextView) view.findViewById(R.id.text_time_wed);
            this.text_class_thur = (TextView) view.findViewById(R.id.text_class_thur);
            this.text_subject_thur = (TextView) view.findViewById(R.id.text_subject_thur);
            this.text_time_thur = (TextView) view.findViewById(R.id.text_time_thur);
            this.text_subject_fri = (TextView) view.findViewById(R.id.text_subject_fri);
            this.text_class_fri = (TextView) view.findViewById(R.id.text_class_fri);
            this.text_time_fri = (TextView) view.findViewById(R.id.text_time_fri);
            this.text_subject_sat = (TextView) view.findViewById(R.id.text_subject_sat);
            this.text_class_sat = (TextView) view.findViewById(R.id.text_class_sat);
            this.text_time_sat = (TextView) view.findViewById(R.id.text_time_sat);

        }


    }


}

