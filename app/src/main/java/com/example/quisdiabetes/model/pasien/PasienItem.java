package com.example.quisdiabetes.model.pasien;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PasienItem implements Parcelable {

	@SerializedName("provinsi")
	private String provinsi;

	@SerializedName("is_puasa")
	private boolean isPuasa;

	@SerializedName("uuip")
	private String uuip;

	@SerializedName("umur")
	private String umur;

	@SerializedName("pekerjaan")
	private String pekerjaan;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("question")
	private List<QuestionItem> question;

	@SerializedName("rasio_dm")
	private String rasioDm;

	@SerializedName("lama_puasa")
	private String lamaPuasa;

	@SerializedName("jenkel")
	private String jenkel;

	@SerializedName("uuid")
	private String uuid;

	@SerializedName("alasan")
	private List<String> alasan;

	public void setProvinsi(String provinsi){
		this.provinsi = provinsi;
	}

	public String getProvinsi(){
		return provinsi;
	}

	public void setIsPuasa(boolean isPuasa){
		this.isPuasa = isPuasa;
	}

	public boolean isIsPuasa(){
		return isPuasa;
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

	public void setNoHp(String noHp){
		this.noHp = noHp;
	}

	public String getNoHp(){
		return noHp;
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

	public void setLamaPuasa(String lamaPuasa){
		this.lamaPuasa = lamaPuasa;
	}

	public String getLamaPuasa(){
		return lamaPuasa;
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

	public void setAlasan(List<String> alasan){
		this.alasan = alasan;
	}

	public List<String> getAlasan(){
		return alasan;
	}

	@Override
 	public String toString(){
		return 
			"PasienItem{" + 
			"provinsi = '" + provinsi + '\'' + 
			",is_puasa = '" + isPuasa + '\'' + 
			",uuip = '" + uuip + '\'' + 
			",umur = '" + umur + '\'' + 
			",pekerjaan = '" + pekerjaan + '\'' + 
			",no_hp = '" + noHp + '\'' + 
			",question = '" + question + '\'' + 
			",rasio_dm = '" + rasioDm + '\'' + 
			",lama_puasa = '" + lamaPuasa + '\'' + 
			",jenkel = '" + jenkel + '\'' + 
			",uuid = '" + uuid + '\'' + 
			",alasan = '" + alasan + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.provinsi);
		dest.writeByte(this.isPuasa ? (byte) 1 : (byte) 0);
		dest.writeString(this.uuip);
		dest.writeString(this.umur);
		dest.writeString(this.pekerjaan);
		dest.writeString(this.noHp);
		dest.writeList(this.question);
		dest.writeString(this.rasioDm);
		dest.writeString(this.lamaPuasa);
		dest.writeString(this.jenkel);
		dest.writeString(this.uuid);
		dest.writeStringList(this.alasan);
	}

	public PasienItem() {
	}

	protected PasienItem(Parcel in) {
		this.provinsi = in.readString();
		this.isPuasa = in.readByte() != 0;
		this.uuip = in.readString();
		this.umur = in.readString();
		this.pekerjaan = in.readString();
		this.noHp = in.readString();
		this.question = new ArrayList<QuestionItem>();
		in.readList(this.question, QuestionItem.class.getClassLoader());
		this.rasioDm = in.readString();
		this.lamaPuasa = in.readString();
		this.jenkel = in.readString();
		this.uuid = in.readString();
		this.alasan = in.createStringArrayList();
	}

	public static final Parcelable.Creator<PasienItem> CREATOR = new Parcelable.Creator<PasienItem>() {
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