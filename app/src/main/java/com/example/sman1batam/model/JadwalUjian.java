package com.example.sman1batam.model;

import com.google.gson.annotations.SerializedName;

public class JadwalUjian {

	@SerializedName("file_pdf")
	private String filePdf;

	@SerializedName("nama_jadwal_Ujian")
	private String namaJadwalUjian;

	@SerializedName("updated_at")
	private String updatedAt;

	@SerializedName("kode_jadwal_Ujian")
	private String kodeJadwalUjian;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	public void setFilePdf(String filePdf){
		this.filePdf = filePdf;
	}

	public String getFilePdf(){
		return filePdf;
	}

	public void setNamaJadwalUjian(String namaJadwalUjian){
		this.namaJadwalUjian = namaJadwalUjian;
	}

	public String getNamaJadwalUjian(){
		return namaJadwalUjian;
	}

	public void setUpdatedAt(String updatedAt){
		this.updatedAt = updatedAt;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setKodeJadwalUjian(String kodeJadwalUjian){
		this.kodeJadwalUjian = kodeJadwalUjian;
	}

	public String getKodeJadwalUjian(){
		return kodeJadwalUjian;
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