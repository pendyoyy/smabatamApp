package com.example.sman1batam.model;

import com.google.gson.annotations.SerializedName;

public class Jadwal {

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("kode_jadwal")
	private String kodeJadwal;

	@SerializedName("document")
	private String document;

	@SerializedName("nama_jadwal")
	private String namaJadwal;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setKodeJadwal(String kodeJadwal){
		this.kodeJadwal = kodeJadwal;
	}

	public String getKodeJadwal(){
		return kodeJadwal;
	}

	public void setDocument(String document){
		this.document = document;
	}

	public String getDocument(){
		return document;
	}

	public void setNamaJadwal(String namaJadwal){
		this.namaJadwal = namaJadwal;
	}

	public String getNamaJadwal(){
		return namaJadwal;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}
}