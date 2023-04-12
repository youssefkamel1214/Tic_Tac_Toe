package com.example.tictactoe.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.tictactoe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Boolean Xplays=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.changeroles.setOnClickListener(v -> {
            Xplays^=true;
           if(Xplays){
               binding.playertextvalue1.setText("X");
               binding.playertextvalue2.setText("O");
           }
           else {
               binding.playertextvalue1.setText("O");
               binding.playertextvalue2.setText("X");
           }
        });
        binding.play.setOnClickListener(v -> {
            Intent intent=new Intent(MainActivity.this, GameActivity.class);
            intent.putExtra("Xplay", Xplays);
            startActivity(intent);
        });

    }
}