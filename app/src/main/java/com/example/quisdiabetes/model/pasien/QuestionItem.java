package com.example.quisdiabetes.model.pasien;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class QuestionItem implements Parcelable {

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

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.date);
		dest.writeString(this.question);
		dest.writeString(this.answer);
		dest.writeString(this.skor);
	}

	public QuestionItem() {
	}

	protected QuestionItem(Parcel in) {
		this.date = in.readString();
		this.question = in.readString();
		this.answer = in.readString();
		this.skor = in.readString();
	}

	public static final Parcelable.Creator<QuestionItem> CREATOR = new Parcelable.Creator<QuestionItem>() {
		@Override
		public QuestionItem createFromParcel(Parcel source) {
			return new QuestionItem(source);
		}

		@Override
		public QuestionItem[] newArray(int size) {
			return new QuestionItem[size];
		}
	};
}