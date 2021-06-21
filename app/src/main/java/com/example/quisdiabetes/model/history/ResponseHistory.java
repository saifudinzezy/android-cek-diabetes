package com.example.quisdiabetes.model.history;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseHistory{

	@SerializedName("pesan")
	private String pesan;

	@SerializedName("response")
	private boolean response;

	@SerializedName("history")
	private List<HistoryItem> history;

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

	public void setHistory(List<HistoryItem> history){
		this.history = history;
	}

	public List<HistoryItem> getHistory(){
		return history;
	}
}