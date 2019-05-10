package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.spark.submitbutton.SubmitButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewFlipper viewFlipper;
    EditText question, answer, edtId;
    SubmitButton add, update, delete;

    ListView frstquestions;

    List<Quiz> data = new ArrayList<>();

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);


        question = findViewById(R.id.question_messageID);
        answer = findViewById(R.id.answer_message);
        edtId = findViewById(R.id.edtId);
        add = findViewById(R.id.add_ID);
        update = findViewById(R.id.update_ID);
        delete = findViewById(R.id.delete_ID);
        viewFlipper = (ViewFlipper) findViewById(R.id.v_flipper);
        frstquestions = (ListView) findViewById(R.id.list);




        int images[] = {R.drawable.quiz1, R.drawable.quiz2, R.drawable.quiz3};

        for (int image : images) {
            flipperImages(image);
        }

        refreshData();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quiz quiz = new Quiz(Integer.parseInt(edtId.getText().toString()), question.getText().toString(), answer.getText().toString());
                databaseHelper.addData(quiz);
                refreshData();
                Toast.makeText(getApplicationContext(), "Data added successfully", Toast.LENGTH_SHORT).show();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quiz quiz = new Quiz(Integer.parseInt(edtId.getText().toString()), question.getText().toString(), answer.getText().toString());
                databaseHelper.updateData(quiz);
                refreshData();
                Toast.makeText(getApplicationContext(), "Data updated successfully", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Quiz quiz = new Quiz(Integer.parseInt(edtId.getText().toString()), question.getText().toString(), answer.getText().toString());
                databaseHelper.deleteData(quiz);
                refreshData();
                Toast.makeText(getApplicationContext(), "Data deleted successfully", Toast.LENGTH_SHORT).show();
            }
        });



    }


    private void flipperImages(int image) {

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        //animation

        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);


    }

    private void refreshData(){
        data = databaseHelper.getAllQuiz();
        QuizAdapter quizAdapter = new QuizAdapter(MainActivity.this, data, edtId, question, answer);
        frstquestions.setAdapter(quizAdapter);
    }

}
