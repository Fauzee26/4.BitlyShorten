package fauzi.hilmy.bitlyshorten.model;

import com.google.gson.annotations.SerializedName;

public class ResponseUrl{

	@SerializedName("status_code")
	private int statusCode;

	@SerializedName("data")
	private Data data;

	@SerializedName("status_txt")
	private String statusTxt;

	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
	}

	public int getStatusCode(){
		return statusCode;
	}

	public void setData(Data data){
		this.data = data;
	}

	public Data getData(){
		return data;
	}

	public void setStatusTxt(String statusTxt){
		this.statusTxt = statusTxt;
	}

	public String getStatusTxt(){
		return statusTxt;
	}
}