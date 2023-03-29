package com.example.quisdiabetesi.model;

import com.google.gson.annotations.SerializedName;

public class ResponseCRUD{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("response")
	private boolean response;

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
}