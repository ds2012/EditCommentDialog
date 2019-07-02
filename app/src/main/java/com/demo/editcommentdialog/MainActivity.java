package com.demo.editcommentdialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo.editcommentdialog.view.EditCommentDialog;

public class MainActivity extends AppCompatActivity {

    private Button openDialog;
    private EditCommentDialog editCommentDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView(){
        openDialog = (Button)findViewById(R.id.open_dialog_btn);
        editCommentDialog = new EditCommentDialog(MainActivity.this, R.style.ListDialog);
        editCommentDialog.initLayoutNormal();
    }

    private void initListener(){
        openDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editCommentDialog.pop();
            }
        });
    }
}
