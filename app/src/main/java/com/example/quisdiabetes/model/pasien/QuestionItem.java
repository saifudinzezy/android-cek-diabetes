package com.example.quisdiabetes.model.pasien;

import com.google.gson.annotations.SerializedName;

public class QuestionItem{

	@SerializedName("date")
	private String date;

	@SerializedName("question")
	private String question;

	@SerializedName("answer")
	private String answer;

	@SerializedName("skor")
	private String skor;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setQuestion(String question){
		this.question = question;
	}

	public String getQuestion(){
		return question;
	}

	public void setAnswer(String answer){
		this.answer = answer;
	}

	public String getAnswer(){
		return answer;
	}

	public void setSkor(String skor){
		this.skor = skor;
	}

	public String getSkor(){
		return skor;
	}

	@Override
 	public String toString(){
		return 
			"QuestionItem{" + 
			"date = '" + date + '\'' + 
			",question = '" + question + '\'' + 
			",answer = '" + answer + '\'' + 
			",skor = '" + skor + '\'' + 
			"}";
		}
}