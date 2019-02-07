package com.example.studytools.activity.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.studytools.R;
import com.example.studytools.data.preference.AppPreference;
import com.example.studytools.interfaces.CallBack;
import com.example.studytools.models.Answer;
import com.example.studytools.models.Exam;
import com.example.studytools.network.DataHandler;

import java.util.ArrayList;

import static com.example.studytools.activity.student.StudentQuizActivity.EXTRA_EXAM_ID;
import static com.example.studytools.activity.student.StudentQuizActivity.EXTRA_SCORE;

public class StudentExamList extends AppCompatActivity {
    public static final int REQUEST_QUIZ_CODE = 1;

    private ArrayList<Exam> examList;

    private Spinner spinnerSemestersExamlist;

    private TextView tv_result;
    private TextView tv_summary;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_exam_list);

        spinnerSemestersExamlist = findViewById(R.id.spinner_semesters_examlist);
        tv_result = findViewById(R.id.result);
        tv_summary = findViewById(R.id.summary);

        recyclerView = findViewById(R.id.recyclerView_examList);


        String semesters[] = {"Select Semester", "1st Semester", "2nd Semester", "3rd Semester",
                "4th Semester", "5th Semester", "6th Semester", "7th Semester", "8th Semester"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, semesters);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemestersExamlist.setAdapter(adapter);

        spinnerSemestersExamlist.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position != 0) {
                    DataHandler.getExamList(StudentExamList.this, new CallBack<Exam>() {
                        @Override
                        public void onSuccess(ArrayList<Exam> arrayList) {
                            examList = arrayList;
                        }

                        @Override
                        public void onFail(String msg) {

                        }
                    }, Integer.parseInt(AppPreference.getUserId(StudentExamList.this)), position);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_QUIZ_CODE && resultCode == RESULT_OK) {
            int score = data.getIntExtra(EXTRA_SCORE, 0);
            int examid = data.getIntExtra(EXTRA_EXAM_ID, 0);
            int userid = Integer.parseInt(AppPreference.getUserId(StudentExamList.this));

            ArrayList<Answer> answeredList = data.getParcelableArrayListExtra("answeredList");
            ArrayList<Answer> answerList = data.getParcelableArrayListExtra("answerList");

            DataHandler.storeResult(this, userid, examid, score);

            spinnerSemestersExamlist.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);

            tv_result.setVisibility(View.VISIBLE);
            tv_summary.setVisibility(View.VISIBLE);

            String result = "";

            for (int i = 0; i < answeredList.size(); i++) {
                result += "Question No. " + (i + 1) + "\nCorrect : " + getChar(answerList.get(i).getAnswer())
                        + "\nAnswered : " + getChar(answeredList.get(i).getAnswer()) + "\n\n";
            }

            tv_summary.setText(result);
            tv_result.setText("Score : "+score);

            //finish();


        }
    }


    private char getChar(int n) {
        switch (n) {
            case 1:
                return 'A';
            case 2:
                return 'B';
            default:
                return 'C';
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.button_menu_logout:
                DataHandler.logout(this);
                break;
        }

        return super.onOptionsItemSelected(item);
    }


}