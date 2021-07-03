package com.example.quisdiabetes.model.pasien;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponsePasien{

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
}