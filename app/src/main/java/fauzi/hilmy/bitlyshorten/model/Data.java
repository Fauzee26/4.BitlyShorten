package fauzi.hilmy.bitlyshorten.model;

import com.google.gson.annotations.SerializedName;

public class Data{

	@SerializedName("global_hash")
	private String globalHash;

	@SerializedName("long_url")
	private String longUrl;

	@SerializedName("url")
	private String url;

	@SerializedName("hash")
	private String hash;

	@SerializedName("new_hash")
	private int newHash;

	public void setGlobalHash(String globalHash){
		this.globalHash = globalHash;
	}

	public String getGlobalHash(){
		return globalHash;
	}

	public void setLongUrl(String longUrl){
		this.longUrl = longUrl;
	}

	public String getLongUrl(){
		return longUrl;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setHash(String hash){
		this.hash = hash;
	}

	public String getHash(){
		return hash;
	}

	public void setNewHash(int newHash){
		this.newHash = newHash;
	}

	public int getNewHash(){
		return newHash;
	}
}