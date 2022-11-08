package com.huawei.android.appdovui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvQuestion;
    private TextView tvContentQuestion;
    private TextView tvAnswer1,tvAnswer2,tvAnswer3,tvAnswer4;

    private List<Question> mListQuestion;
    private Question mQuestion;
    private int currentQuestion = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUi();
        mListQuestion = getListQuestion();
        if(mListQuestion.isEmpty()){
            return;
        }
        setDataQuestion(mListQuestion.get(currentQuestion));


    }

    private void setDataQuestion(Question question) {
        if(question == null ){
            return;
        }
        mQuestion = question;

        tvAnswer1.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tvAnswer2.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tvAnswer3.setBackgroundResource(R.drawable.bg_blue_corner_30);
        tvAnswer4.setBackgroundResource(R.drawable.bg_blue_corner_30);

        String titleQuestion = "Question " + question.getNumber();
        tvQuestion.setText(titleQuestion);
        tvContentQuestion.setText(question.getContent());
        tvAnswer1.setText(question.getListAnswer().get(0).getContent());
        tvAnswer2.setText(question.getListAnswer().get(1).getContent());
        tvAnswer3.setText(question.getListAnswer().get(2).getContent());
        tvAnswer4.setText(question.getListAnswer().get(3).getContent());

        tvAnswer1.setOnClickListener(this);
        tvAnswer2.setOnClickListener(this);
        tvAnswer3.setOnClickListener(this);
        tvAnswer4.setOnClickListener(this);
    }

    private void initUi(){
        tvQuestion = findViewById(R.id.tv_question);
        tvContentQuestion = findViewById(R.id.tv_content_question);
        tvAnswer1 = findViewById(R.id.tv_answer1);
        tvAnswer2 = findViewById(R.id.tv_answer2);
        tvAnswer3 = findViewById(R.id.tv_answer3);
        tvAnswer4 = findViewById(R.id.tv_answer4);
    }

    private List<Question> getListQuestion(){
        List<Question> list = new ArrayList<>();

        List<Answer> answerList1 = new ArrayList<>();
        answerList1.add(new Answer("Gà",true));
        answerList1.add(new Answer("Cá",false));
        answerList1.add(new Answer("Bò",false));
        answerList1.add(new Answer("Lợn",false));

        List<Answer> answerList2 = new ArrayList<>();
        answerList2.add(new Answer("Cá",false));
        answerList2.add(new Answer("Đá",true));
        answerList2.add(new Answer("Gối",false));
        answerList2.add(new Answer("Nước",false));

        List<Answer> answerList3 = new ArrayList<>();
        answerList3.add(new Answer("Thái Nguyên",false));
        answerList3.add(new Answer("Đà Nẵng",false));
        answerList3.add(new Answer("Hà Nội",true));
        answerList3.add(new Answer("Hải Phòng",false));

        list.add(new Question(1,"Con nào là gia cầm?",answerList1));
        list.add(new Question(2,"Chân cứng,.... mềm?",answerList2));
        list.add(new Question(3,"Thủ đô của Việt Nam là gì?",answerList3));

        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_answer1:
                tvAnswer1.setBackgroundResource(R.drawable.bg_orange_corner_30);
                checkAnswer(tvAnswer1,mQuestion,mQuestion.getListAnswer().get(0));
                break;

            case R.id.tv_answer2:
                tvAnswer2.setBackgroundResource(R.drawable.bg_orange_corner_30);
                checkAnswer(tvAnswer2,mQuestion,mQuestion.getListAnswer().get(1));

                break;

            case R.id.tv_answer3:
                tvAnswer3.setBackgroundResource(R.drawable.bg_orange_corner_30);
                checkAnswer(tvAnswer3,mQuestion,mQuestion.getListAnswer().get(2));

                break;

            case R.id.tv_answer4:
                tvAnswer4.setBackgroundResource(R.drawable.bg_orange_corner_30);
                checkAnswer(tvAnswer4,mQuestion,mQuestion.getListAnswer().get(3));

                break;
        }

    }
    private void checkAnswer(TextView textView,Question question, Answer answer){
            if(answer.isCorrect()){
                textView.setBackgroundResource(R.drawable.bg_green_corner_30);
                nextQuestion();
            } else {
                textView.setBackgroundResource(R.drawable.bg_red_corner_30);
                showAnswerCorrect(question);
                gameOver();
            }
    }

    private void gameOver() {
        showDialog("Game Over");
    }

    private void showAnswerCorrect(Question question) {
        if (question == null || question.getListAnswer() == null || question.getListAnswer().isEmpty()){
            return;
        }
        if (question.getListAnswer().get(0).isCorrect()) {
            tvAnswer1.setBackgroundResource(R.drawable.bg_green_corner_30);
        } else if (question.getListAnswer().get(1).isCorrect()){
            tvAnswer2.setBackgroundResource(R.drawable.bg_green_corner_30);
        } else if (question.getListAnswer().get(2).isCorrect()){
            tvAnswer3.setBackgroundResource(R.drawable.bg_green_corner_30);
        } else if (question.getListAnswer().get(3).isCorrect()){
            tvAnswer4.setBackgroundResource(R.drawable.bg_green_corner_30);
        }
    }

    private void nextQuestion() {
        if (currentQuestion == mListQuestion.size()-1){
            showDialog("You win");
        } else {
            currentQuestion ++;
            setDataQuestion(mListQuestion.get(currentQuestion));
        }
    }
    private void showDialog(String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currentQuestion = 0;
                setDataQuestion(mListQuestion.get(currentQuestion));
                dialog.dismiss();;
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}