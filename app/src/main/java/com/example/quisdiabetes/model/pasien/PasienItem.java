package com.example.quisdiabetes.model.pasien;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PasienItem implements Parcelable {

	@SerializedName("provinsi")
	private String provinsi;

	@SerializedName("uuip")
	private String uuip;

	@SerializedName("umur")
	private String umur;

	@SerializedName("pekerjaan")
	private String pekerjaan;

	@SerializedName("question")
	private List<QuestionItem> question;

	@SerializedName("rasio_dm")
	private String rasioDm;

	@SerializedName("jenkel")
	private String jenkel;

	@SerializedName("uuid")
	private String uuid;

	@SerializedName("no_hp")
	private String noHp;

	public String getNoHp() {
		return noHp;
	}

	public void setNoHp(String noHp) {
		this.noHp = noHp;
	}

	public void setProvinsi(String provinsi){
		this.provinsi = provinsi;
	}

	public String getProvinsi(){
		return provinsi;
	}

	public void setUuip(String uuip){
		this.uuip = uuip;
	}

	public String getUuip(){
		return uuip;
	}

	public void setUmur(String umur){
		this.umur = umur;
	}

	public String getUmur(){
		return umur;
	}

	public void setPekerjaan(String pekerjaan){
		this.pekerjaan = pekerjaan;
	}

	public String getPekerjaan(){
		return pekerjaan;
	}

	public void setQuestion(List<QuestionItem> question){
		this.question = question;
	}

	public List<QuestionItem> getQuestion(){
		return question;
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

	public PasienItem() {
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.provinsi);
		dest.writeString(this.uuip);
		dest.writeString(this.umur);
		dest.writeString(this.pekerjaan);
		dest.writeTypedList(this.question);
		dest.writeString(this.rasioDm);
		dest.writeString(this.jenkel);
		dest.writeString(this.uuid);
		dest.writeString(this.noHp);
	}

	protected PasienItem(Parcel in) {
		this.provinsi = in.readString();
		this.uuip = in.readString();
		this.umur = in.readString();
		this.pekerjaan = in.readString();
		this.question = in.createTypedArrayList(QuestionItem.CREATOR);
		this.rasioDm = in.readString();
		this.jenkel = in.readString();
		this.uuid = in.readString();
		this.noHp = in.readString();
	}

	public static final Creator<PasienItem> CREATOR = new Creator<PasienItem>() {
		@Override
		public PasienItem createFromParcel(Parcel source) {
			return new PasienItem(source);
		}

		@Override
		public PasienItem[] newArray(int size) {
			return new PasienItem[size];
		}
	};
}