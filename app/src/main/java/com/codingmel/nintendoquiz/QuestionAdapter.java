package com.codingmel.nintendoquiz;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private List<Question> questions;

    public QuestionAdapter(List<Question> questions) {
        this.questions = questions;
    }

    @NonNull
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_question,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionAdapter.ViewHolder holder, int position) {
        final Question question = questions.get(position);
        holder.questionImg.setImageResource(question.getImgId());
        holder.question.setText(question.getQuestion());
        holder.difficulty.setText(question.getDifficulty());

        //For start a quiz with only one question
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context,GameActivity.class);
                ArrayList<Question> questionList = new ArrayList<Question>();
                questionList.add(question);//Create a list with one question (Because the game activity take a list of questions)
                intent.putExtra("questions",questionList);
                intent.putExtra("difficulty",question.getDifficulty());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        final ImageView questionImg;
        final TextView question;
        final TextView difficulty;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            questionImg = itemView.findViewById(R.id.questionIcon);
            question = itemView.findViewById(R.id.questionTextView);
            difficulty = itemView.findViewById(R.id.difficultyTextView);
        }
    }
}
