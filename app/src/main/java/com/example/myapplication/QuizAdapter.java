package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class QuizAdapter extends BaseAdapter {
    Activity activity;
    List<Quiz> frstquiz;
    LayoutInflater inflater;
    EditText edtId, questn, answer;

    public QuizAdapter(Activity activity, List<Quiz> frstquiz, EditText edtId, EditText questn, EditText answer) {
        this.activity = activity;
        this.frstquiz = frstquiz;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.edtId = edtId;
        this.questn = questn;
        this.answer = answer;
    }

    @Override
    public int getCount() {
        return frstquiz.size();
    }

    @Override
    public Object getItem(int position) {
        return frstquiz.get(position);
    }

    @Override
    public long getItemId(int position) {
        return frstquiz.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.row,null);
        final TextView txtRowId, txtQuestn, txtAnswer;

        txtRowId = (TextView)rowView.findViewById(R.id.txtRowId);
        txtQuestn = (TextView)rowView.findViewById(R.id.txtRowQunts);
        txtAnswer = (TextView)rowView.findViewById(R.id.txtRowAnswer);

        txtRowId.setText(""+frstquiz.get(position).getId());
        txtQuestn.setText(""+frstquiz.get(position).getQuestions());
        txtAnswer.setText(""+frstquiz.get(position).getAnswers());

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtId.setText(""+txtRowId.getText());
                questn.setText(""+txtQuestn.getText());
                answer.setText(""+txtAnswer.getText());

            }
        });
        return rowView;
    }
}
