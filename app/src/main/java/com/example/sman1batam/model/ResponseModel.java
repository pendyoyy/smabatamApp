package com.example.sman1batam.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseModel{

	@SerializedName("success")
	private int success;

	@SerializedName("massage")
	private String massage;

	@SerializedName("user")
	private User user;

	@SerializedName("jadwals")
	private List<Jadwal> jadwals;

	@SerializedName("jadwal_ujians")
	private List<JadwalUjian> jadwalUjians;



	public void setSuccess(int success){
		this.success = success;
	}

	public int getSuccess(){
		return success;
	}

	public void setMassage(String massage){
		this.massage = massage;
	}

	public String getMassage(){
		return massage;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	public void setJadwals(List<Jadwal> jadwals){
		this.jadwals = jadwals;
	}

	public List<Jadwal> getJadwals(){
		return jadwals;
	}

	public void setJadwalUjians(List<JadwalUjian> jadwalUjians){
		this.jadwalUjians = jadwalUjians;
	}

	public List<JadwalUjian> getJadwalUjians(){
		return jadwalUjians;
	}
}