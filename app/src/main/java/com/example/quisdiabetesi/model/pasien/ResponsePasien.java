package com.example.quisdiabetesi.model.pasien;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponsePasien implements Parcelable {

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("response")
	private boolean response;

	@SerializedName("pasien")
	private List<PasienItem> pasien;

	public void setPesan(String pesan){
		this.pesan = pesan;
	}

	public String getPesan(){
		return pesan;
	}

	public void setResponse(boolean response){
		this.response = response;
	}

	public boolean isResponse(){
		return response;
	}

	public void setPasien(List<PasienItem> pasien){
		this.pasien = pasien;
	}

	public List<PasienItem> getPasien(){
		return pasien;
	}

	@Override
 	public String toString(){
		return 
			"ResponsePasien{" + 
			"pesan = '" + pesan + '\'' + 
			",response = '" + response + '\'' + 
			",pasien = '" + pasien + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(this.pesan);
		dest.writeByte(this.response ? (byte) 1 : (byte) 0);
		dest.writeTypedList(this.pasien);
	}

	public ResponsePasien() {
	}

	protected ResponsePasien(Parcel in) {
		this.pesan = in.readString();
		this.response = in.readByte() != 0;
		this.pasien = in.createTypedArrayList(PasienItem.CREATOR);
	}

	public static final Parcelable.Creator<ResponsePasien> CREATOR = new Parcelable.Creator<ResponsePasien>() {
		@Override
		public ResponsePasien createFromParcel(Parcel source) {
			return new ResponsePasien(source);
		}

		@Override
		public ResponsePasien[] newArray(int size) {
			return new ResponsePasien[size];
		}
	};
}