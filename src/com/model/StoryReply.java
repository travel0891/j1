package com.model;

public class StoryReply {

	private int reply_id;
	private int list_id;
	private String reply_text;
	private int create_begin_id;
	private String create_begin_text;
	private String create_time;

	public int getReply_id() {
		return reply_id;
	}

	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}

	public int getList_id() {
		return list_id;
	}

	public void setList_id(int list_id) {
		this.list_id = list_id;
	}

	public String getReply_text() {
		return reply_text;
	}

	public void setReply_text(String reply_text) {
		this.reply_text = reply_text;
	}

	public int getCreate_begin_id() {
		return create_begin_id;
	}

	public void setCreate_begin_id(int create_begin_id) {
		this.create_begin_id = create_begin_id;
	}

	public String getCreate_begin_text() {
		return create_begin_text;
	}

	public void setCreate_begin_text(String create_begin_text) {
		this.create_begin_text = create_begin_text;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "StoryReply [reply_id=" + reply_id + ", list_id=" + list_id + ", reply_text=" + reply_text
				+ ", create_begin_id=" + create_begin_id + ", create_begin_text=" + create_begin_text + ", create_time="
				+ create_time + "]";
	}
}