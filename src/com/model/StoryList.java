package com.model;

public class StoryList {
	private int list_count;
	private int list_id;
	private String source_text;
	private String state_text;
	private String type_text;
	private String story_text;
	private String handle_text;
	private String create_text;
	private String create_user_photo;
	private String create_time;
	private int read_count;
	private int reply_count;
	private int has_read;

	private String source_tag;
	private String source_tel;
	private String story_context;

	public int getList_count() {
		return list_count;
	}

	public void setList_count(int list_count) {
		this.list_count = list_count;
	}

	public int getList_id() {
		return list_id;
	}

	public void setList_id(int list_id) {
		this.list_id = list_id;
	}

	public String getSource_text() {
		return source_text;
	}

	public void setSource_text(String source_text) {
		this.source_text = source_text;
	}

	public String getState_text() {
		return state_text;
	}

	public void setState_text(String state_text) {
		this.state_text = state_text;
	}

	public String getType_text() {
		return type_text;
	}

	public void setType_text(String type_text) {
		this.type_text = type_text;
	}

	public String getStory_text() {
		return story_text;
	}

	public void setStory_text(String story_text) {
		this.story_text = story_text;
	}

	public String getHandle_text() {
		return handle_text;
	}

	public void setHandle_text(String handle_text) {
		this.handle_text = handle_text;
	}

	public String getCreate_text() {
		return create_text;
	}

	public void setCreate_text(String create_text) {
		this.create_text = create_text;
	}

	public String getCreate_user_photo() {
		return create_user_photo;
	}

	public void setCreate_user_photo(String create_user_photo) {
		this.create_user_photo = create_user_photo;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public int getRead_count() {
		return read_count;
	}

	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}

	public int getReply_count() {
		return reply_count;
	}

	public void setReply_count(int reply_count) {
		this.reply_count = reply_count;
	}

	public int getHas_read() {
		return has_read;
	}

	public void setHas_read(int has_read) {
		this.has_read = has_read;
	}

	public String getSource_tag() {
		return source_tag;
	}

	public void setSource_tag(String source_tag) {
		this.source_tag = source_tag;
	}

	public String getSource_tel() {
		return source_tel;
	}

	public void setSource_tel(String source_tel) {
		this.source_tel = source_tel;
	}

	public String getStory_context() {
		return story_context;
	}

	public void setStory_context(String story_context) {
		this.story_context = story_context;
	}

	@Override
	public String toString() {
		return "StoryList [list_count=" + list_count + ", list_id=" + list_id + ", source_text=" + source_text
				+ ", state_text=" + state_text + ", type_text=" + type_text + ", story_text=" + story_text
				+ ", handle_text=" + handle_text + ", create_text=" + create_text + ", create_user_photo="
				+ create_user_photo + ", create_time=" + create_time + ", read_count=" + read_count + ", reply_count="
				+ reply_count + ", has_read=" + has_read + ", source_tag=" + source_tag + ", source_tel=" + source_tel
				+ ", story_context=" + story_context + "]";
	}
}