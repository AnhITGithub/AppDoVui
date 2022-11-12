package com.huawei.android.appdovui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvQuestion;
    private TextView tvContentQuestion;
    private TextView tvAnswer1,tvAnswer2,tvAnswer3,tvAnswer4;
    private ImageButton thoat, dung;
    private ImageButton ImgNext, ImgPrev;
    private ImageButton setting;
    private MediaPlayer mediaPlayer;



    private List<Question> mListQuestion;
    private Question mQuestion;
    private int currentQuestion = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) { //khoi tao
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer= MediaPlayer.create(MainActivity.this, R.raw.closer);
        initUi();
        mListQuestion = getListQuestion();
        if(mListQuestion.isEmpty()){
            return;
        }
        setDataQuestion(mListQuestion.get(currentQuestion));
        Exit();
        Stop();
        Next();
        Previous();
        Setting();
    }

    private void Setting() {


        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog=new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.dialog_setting);
                CheckBox music=(CheckBox)dialog.findViewById(R.id.song); //chua chay duoc nhac
                music.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(music.isChecked()){
                            mediaPlayer.start();
                            mediaPlayer.isLooping();
                        }else{
                            mediaPlayer.pause();
                    }
                }
            });
                TextView develop=(TextView)dialog.findViewById(R.id.developers);
                develop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog("ĐỘI NGŨ PHÁT TRIỂN APP ĐỐ VUI:" +
                                "\n" +
                                "\nDương Tuấn Anh - 2010A010053" +
                                "\nNguyễn Thị Ngọc Anh - " +
                                "\nPhạm Quang Huy - ");
                    }
                });
                dialog.show();
            }
        });
    }

    private void Next(){
        ImgNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentQuestion ++;
                setDataQuestion(mListQuestion.get(currentQuestion));
            }
        });
    }

    private void Previous() {
        ImgPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentQuestion --;
                setDataQuestion(mListQuestion.get(currentQuestion));
            }
        });
    }

    private void Stop() {
        dung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder.setTitle("Game đang dừng");
                builder.setMessage("Hãy tiếp tục chơi !");
                builder.setIcon(android.R.drawable.ic_dialog_alert);
                builder.setCancelable(false);
                builder.setPositiveButton("Tiếp tục", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });

    }

    private void Exit() {
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
                builder.setTitle("Back to topic list");
                builder.setMessage("Bạn có muốn tiếp tục chơi không !");
                builder.setIcon(android.R.drawable.ic_media_previous);
                builder.setCancelable(false);
                builder.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onBackPressed();
                    }
                });
                builder.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
    }

    private void setDataQuestion(Question question) { //set cau hoi
        if(question == null ){
            return;
        }
        mQuestion = question;

        tvAnswer1.setBackgroundResource(R.drawable.cautraloi_corner);
        tvAnswer2.setBackgroundResource(R.drawable.cautraloi_corner);
        tvAnswer3.setBackgroundResource(R.drawable.cautraloi_corner);
        tvAnswer4.setBackgroundResource(R.drawable.cautraloi_corner);

        String titleQuestion = "Câu hỏi " + question.getNumber();
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

    private void initUi(){ //bat giao dien
        tvQuestion = findViewById(R.id.tv_question);
        tvContentQuestion = findViewById(R.id.tv_content_question);
        tvAnswer1 = findViewById(R.id.tv_answer1);
        tvAnswer2 = findViewById(R.id.tv_answer2);
        tvAnswer3 = findViewById(R.id.tv_answer3);
        tvAnswer4 = findViewById(R.id.tv_answer4);
        thoat=findViewById(R.id.exit);
        dung=findViewById(R.id.stop);
        ImgNext=findViewById(R.id.next);
        ImgPrev=findViewById(R.id.prev);
        setting=findViewById(R.id.caidat);

    }

    private List<Question> getListQuestion(){ //danh sach cau hoi va cai tra loi
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

        List<Answer> answerList4 = new ArrayList<>();
        answerList4.add(new Answer("Ếch",false));
        answerList4.add(new Answer("Cóc",true));
        answerList4.add(new Answer("Thằn lằn",false));
        answerList4.add(new Answer("Nhái",false));

        List<Answer> answerList5 = new ArrayList<>();
        answerList5.add(new Answer("Đá Lăn",false));
        answerList5.add(new Answer("Đá Chạy",false));
        answerList5.add(new Answer("Đá Nhảy",true));
        answerList5.add(new Answer("Đá Bò",false));

        list.add(new Question(1,"Con nào là gia cầm?",answerList1));
        list.add(new Question(2,"Chân cứng,.... mềm?",answerList2));
        list.add(new Question(3,"Thủ đô của Việt Nam là gì?",answerList3));
        list.add(new Question(4,"Đâu là một loại hình chợ tạm tự phát thường xuất hiện trong các khu dân cư?",answerList4));
        list.add(new Question(5,"Đâu là tên một bãi biển đẹp ở Quảng Bình?",answerList5));

        return list;
    }

    @Override
    public void onClick(View v) { //chon cau tra loi
        switch (v.getId()){
            case R.id.tv_answer1:
                tvAnswer1.setBackgroundResource(R.drawable.question_corner);
                checkAnswer(tvAnswer1,mQuestion,mQuestion.getListAnswer().get(0));
                break;

            case R.id.tv_answer2:
                tvAnswer2.setBackgroundResource(R.drawable.question_corner);
                checkAnswer(tvAnswer2,mQuestion,mQuestion.getListAnswer().get(1));

                break;

            case R.id.tv_answer3:
                tvAnswer3.setBackgroundResource(R.drawable.question_corner);
                checkAnswer(tvAnswer3,mQuestion,mQuestion.getListAnswer().get(2));

                break;

            case R.id.tv_answer4:
                tvAnswer4.setBackgroundResource(R.drawable.question_corner);
                checkAnswer(tvAnswer4,mQuestion,mQuestion.getListAnswer().get(3));

                break;
        }

    }
    private void checkAnswer(TextView textView,Question question, Answer answer){ //kiem tra cau tra loi
            if(answer.isCorrect()){
                textView.setBackgroundResource(R.drawable.bg_green_corner_30);
                nextQuestion();
            } else {
                textView.setBackgroundResource(R.drawable.bg_red_corner_30);
                showAnswerCorrect(question);
                gameOver();
            }
    }

    private void gameOver() { //thong bao game ket thuc
        showDialog("Bạn đã thua !");
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

    private void nextQuestion() { //chon dung cau tra loi se chuyen sang cau hoi ke tiep
        if (currentQuestion == mListQuestion.size()-1){ //neu khong con cau hoi phia sau thi hien thong bao chien thang
            AlertDialog.Builder b = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Dialog);
            b.setIcon(android.R.drawable.star_on);
            b.setTitle("Chúc mừng bạn đã chiến thắng !");
            b.setMessage("Bạn có muốn chơi lại không ?");
            b.setNegativeButton("Có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    showDialog("Chiến tiếp thôi nào !");
                }
            });
            b.setPositiveButton("Không", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    onBackPressed();
                }
            });
            b.show();
        } else { //neu con cau hoi phia sau thi chuyen sang cau hoi do
            currentQuestion ++;
            setDataQuestion(mListQuestion.get(currentQuestion));
        }
    }

    private void showDialog(String message){ //thong bao
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                currentQuestion = 0;
                setDataQuestion(mListQuestion.get(currentQuestion));
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}