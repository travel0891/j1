package com.model;

public class StoryBegin {

	private int begin_id;
	private String account;
	private String passwrod;
	private String name_text;
	private String photo_url;

	public int getBegin_id() {
		return begin_id;
	}

	public void setBegin_id(int begin_id) {
		this.begin_id = begin_id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPasswrod() {
		return passwrod;
	}

	public void setPasswrod(String passwrod) {
		this.passwrod = passwrod;
	}

	public String getName_text() {
		return name_text;
	}

	public void setName_text(String name_text) {
		this.name_text = name_text;
	}

	public String getPhoto_url() {
		return photo_url;
	}

	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}

	@Override
	public String toString() {
		return "StoryBegin [begin_id=" + begin_id + ", account=" + account + ", passwrod=" + passwrod + ", name_text="
				+ name_text + ", photo_url=" + photo_url + "]";
	}
}