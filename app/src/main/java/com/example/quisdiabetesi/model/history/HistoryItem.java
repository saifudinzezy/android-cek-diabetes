package com.example.quisdiabetesi.model.history;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class HistoryItem implements Parcelable {

	@SerializedName("date")
	private String date;

	@SerializedName("provinsi")
	private String provinsi;

	@SerializedName("umur")
	private String umur;

	@SerializedName("question")
	private String question;

	@SerializedName("answer")
	private String answer;

	@SerializedName("pekerjaan")
	private String pekerjaan;

	@SerializedName("rasio_dm")
	private String rasioDm;

	@SerializedName("jenkel")
	private String jenkel;

	@SerializedName("uuid")
	private String uuid;

	@SerializedName("skor")
	private String skor;

	public HistoryItem() {
	}

	public HistoryItem(String provinsi, String umur, String pekerjaan, String rasioDm, String jenkel, String uuid) {
		this.provinsi = provinsi;
		this.umur = umur;
		this.pekerjaan = pekerjaan;
		this.rasioDm = rasioDm;
		this.jenkel = jenkel;
		this.uuid = uuid;
	}

	public HistoryItem(String question, String answer, String uuid, String skor) {
		this.question = question;
		this.answer = answer;
		this.uuid = uuid;
		this.skor = skor;
	}

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setProvinsi(String provinsi){
		this.provinsi = provinsi;
	}

	public String getProvinsi(){
		return provinsi;
	}

	public void setUmur(String umur){
		this.umur = umur;
	}

	public String getUmur(){
		return umur;
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

	public void setPekerjaan(String pekerjaan){
		this.pekerjaan = pekerjaan;
	}

	public String getPekerjaan(){
		return pekerjaan;
	}

	public void setRasioDm(String rasioDm){
		this.rasioDm = rasioDm;
	}

	public String getRasioDm(){
		return rasioDm;
	}

	public void setJenkel(String jenkel){
		this.jenkel = jenkel;
	}

	public String getJenkel(){
		return jenkel;
	}

	public void setUuid(String uuid){
		this.uuid = uuid;
	}

	public String getUuid(){
		return uuid;
	}

	public void setSkor(String skor){
		this.skor = skor;
	}

	public String getSkor(){
		return skor;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.date);
		dest.writeString(this.provinsi);
		dest.writeString(this.umur);
		dest.writeString(this.question);
		dest.writeString(this.answer);
		dest.writeString(this.pekerjaan);
		dest.writeString(this.rasioDm);
		dest.writeString(this.jenkel);
		dest.writeString(this.uuid);
		dest.writeString(this.skor);
	}

	protected HistoryItem(Parcel in) {
		this.date = in.readString();
		this.provinsi = in.readString();
		this.umur = in.readString();
		this.question = in.readString();
		this.answer = in.readString();
		this.pekerjaan = in.readString();
		this.rasioDm = in.readString();
		this.jenkel = in.readString();
		this.uuid = in.readString();
		this.skor = in.readString();
	}

	public static final Parcelable.Creator<HistoryItem> CREATOR = new Parcelable.Creator<HistoryItem>() {
		@Override
		public HistoryItem createFromParcel(Parcel source) {
			return new HistoryItem(source);
		}

		@Override
		public HistoryItem[] newArray(int size) {
			return new HistoryItem[size];
		}
	};
}