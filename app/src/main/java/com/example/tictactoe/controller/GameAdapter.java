package com.example.tictactoe.controller;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tictactoe.databinding.RecycleritemBinding;

public class GameAdapter  extends RecyclerView.Adapter<GameAdapter.ViewHolder>{
    int [][]grid=new int[3][3];
    GameManger gameManger;
    boolean Xplay;
    public GameAdapter(GameManger gameManger,boolean beginsX) {
        this.gameManger = gameManger;
        this.Xplay=beginsX;
        for (int i=0;i<3;i++)
            for (int j=0;j<3;j++)
                grid[i][j]=0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecycleritemBinding binding=RecycleritemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
               int row=position/3,column=position%3;
               holder.binding.constraint.setOnClickListener(v -> {
                   if(grid[row][column]!=0)return;
                   if(Xplay) {
                       holder.binding.text.setText("X");
                       grid[row][column]=1;
                   }
                   else {
                       holder.binding.text.setText("O");
                       grid[row][column]=2;
                   }
                   if(checkWin(row,column)){
                        gameManger.onWin();
                   }else if(checkDraw()){
                       gameManger.onDraw();
                   }
                   gameManger.onPlayerChange();
                   Xplay^=true;
               });
               if(grid[row][column]==0){
                   holder.binding.text.setText("");
               }
    }
    public boolean checkDraw() {
        //Check if there is any empty cell in the grid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grid[i][j] == 0) {
                    return false;
                }
            }
        }
        //Return true if the grid is full and there is no winner
        return true;
    }
    private Boolean checkWin(int row, int column) {
        //Check if the row is filled with the same value
        int value = grid[row][column];
        boolean rowWin = true;
        for (int i = 0; i < 3; i++) {
            if (grid[row][i] != value) {
                rowWin = false;
                break;
            }
        }
        //Check if the column is filled with the same value
        boolean colWin = true;
        for (int i = 0; i < 3; i++) {
            if (grid[i][column] != value) {
                colWin = false;
                break;
            }
        }
        //Check if the main diagonal is filled with the same value
        boolean diagWin = true;
        if (row == column) {
            for (int i = 0; i < 3; i++) {
                if (grid[i][i] != value) {
                    diagWin = false;
                    break;
                }
            }
        } else {
            diagWin = false;
        }
        //Check if the anti-diagonal is filled with the same value
        boolean antiDiagWin = true;
        if (row + column == 2) {
            for (int i = 0; i < 3; i++) {
                if (grid[i][2 - i] != value) {
                    antiDiagWin = false;
                    break;
                }
            }
        } else {
            antiDiagWin = false;
        }
        //Return true if any of the four conditions is true
        return rowWin || colWin || diagWin || antiDiagWin;
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    public void clearEveryThing(Boolean tmp) {
        Xplay=tmp;
        for (int i=0;i<3;i++)
            for (int j=0;j<3;j++)
                grid[i][j]=0;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        RecycleritemBinding binding;

        public ViewHolder( RecycleritemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
