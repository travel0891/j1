package com.model;

public class StoryPage {
	private int page_id;
	private int list_id;
	private String page_user_text;
	private String page_user_photo;

	public int getPage_id() {
		return page_id;
	}

	public void setPage_id(int page_id) {
		this.page_id = page_id;
	}

	public int getList_id() {
		return list_id;
	}

	public void setList_id(int list_id) {
		this.list_id = list_id;
	}

	public String getPage_user_text() {
		return page_user_text;
	}

	public void setPage_user_text(String page_user_text) {
		this.page_user_text = page_user_text;
	}

	public String getPage_user_photo() {
		return page_user_photo;
	}

	public void setPage_user_photo(String page_user_photo) {
		this.page_user_photo = page_user_photo;
	}

	@Override
	public String toString() {
		return "StoryPage [page_id=" + page_id + ", list_id=" + list_id + ", page_user_text=" + page_user_text
				+ ", page_user_photo=" + page_user_photo + "]";
	}
}