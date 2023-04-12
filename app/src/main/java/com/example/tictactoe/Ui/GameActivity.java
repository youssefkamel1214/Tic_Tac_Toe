package com.example.tictactoe.Ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.TextView;

import com.example.tictactoe.R;
import com.example.tictactoe.controller.GameAdapter;
import com.example.tictactoe.controller.GameManger;
import com.example.tictactoe.databinding.ActivityGameBinding;
import com.example.tictactoe.databinding.CustomBarDialogBinding;

public class GameActivity extends AppCompatActivity implements GameManger {
    ActivityGameBinding binding;
    Boolean firstPlayer=true;
     GameAdapter gameAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityGameBinding.inflate(getLayoutInflater());
        Boolean tmp=getIntent().getBooleanExtra("Xplay", true);
        gameAdapter=new GameAdapter(this,tmp);
        binding.recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        binding.recyclerView.setAdapter(gameAdapter);
        setContentView(binding.getRoot());
    }

    @Override
    public void onWin() {
        Dialog dialog = new Dialog(GameActivity.this);
        CustomBarDialogBinding dialogBinding=CustomBarDialogBinding.inflate(getLayoutInflater());
        dialog.setContentView(dialogBinding.getRoot());
        if(firstPlayer)
            dialogBinding.title.setText("player 1 wins");
        else
            dialogBinding.title.setText("player 2 wins");
        dialogBinding.continuetxt.setOnClickListener(view -> {
            restartGameAgain();
            dialog.dismiss();
        });
        dialog.show();
    }

    private void restartGameAgain() {
            Boolean tmp=getIntent().getBooleanExtra("Xplay", true);
            firstPlayer=true;
            if(firstPlayer){
                binding.textView2.setText("player 1 turn");
            }
            else {
                binding.textView2.setText("player 2 turn");

            }
            gameAdapter.clearEveryThing(tmp);
    }

    @Override
    public void onPlayerChange() {
                  firstPlayer^=true;
                  if(firstPlayer){
                      binding.textView2.setText("player 1 turn");
                  }
                  else {
                      binding.textView2.setText("player 2 turn");

                  }
    }

    @Override
    public void onDraw() {
        Dialog dialog = new Dialog(GameActivity.this);
        CustomBarDialogBinding dialogBinding=CustomBarDialogBinding.inflate(getLayoutInflater());
        dialog.setContentView(dialogBinding.getRoot());
        dialogBinding.title.setText("Draw no Winners");
        dialogBinding.continuetxt.setOnClickListener(view ->{
            restartGameAgain();
            dialog.dismiss();
        });
        dialog.show();
    }
}