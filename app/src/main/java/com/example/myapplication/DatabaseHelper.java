package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "quiz.db";
    public static final String TABLE_NAME = "quizTable";
    public static final String COL1 = "Id";
    public static final String COL2 = "QUESTIONS";
    public static final String COL3 = "ANSWERS";



    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "QUESTIONS TEXT, ANSWERS TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addData(Quiz quiz){
            SQLiteDatabase db = this.getWritableDatabase();
           ContentValues contentValues = new ContentValues();
           contentValues.put(COL2, quiz.getQuestions());
           contentValues.put(COL3, quiz.getAnswers());

           db.insert(TABLE_NAME, null, contentValues);
           db.close();
    }

    public int updateData(Quiz quiz){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, quiz.getQuestions());
        contentValues.put(COL3, quiz.getAnswers());

        return db.update(TABLE_NAME,contentValues,COL1+" =?",new String[]{String.valueOf(quiz.getId())});
    }

    public void deleteData(Quiz quiz){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,COL1+" =?",new String[]{String.valueOf(quiz.getId())});
        db.close();
    }

    public Quiz getQuiz(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,new String[]{COL1,COL2,COL3},COL1+"=?",
                new String[]{String.valueOf(id)},null,null,null, null);

        if (cursor != null)
            cursor.moveToFirst();
        return new Quiz(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
    }

    public List<Quiz> getAllQuiz(){
        List<Quiz> frstquiz = new ArrayList<>();
        String selectQuery = "SELECT * FROM "+ TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()){
            do {
                Quiz quiz = new Quiz();
                quiz.setId(cursor.getInt(0));
                quiz.setQuestions(cursor.getString(1));
                quiz.setAnswers(cursor.getString(2));

                frstquiz.add(quiz);
            }
            while (cursor.moveToNext());
        }
        return  frstquiz;
    }
}
