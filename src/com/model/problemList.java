package com.model;

import java.util.Date;

public class problemList {

	private int id;
	private int problem_source_id;
	private int internal_dpt_id;
	private int internal_emp_id;
	private String problem_status;
	private String stype;
	private String title;
	private String problem_context;
	private int handle_dpt_id;
	private int handle_emp_id;
	private int create_dpt_id;
	private int create_emp_id;
	private Date create_time;
	private Date update_time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProblem_source_id() {
		return problem_source_id;
	}

	public void setProblem_source_id(int problem_source_id) {
		this.problem_source_id = problem_source_id;
	}

	public int getInternal_dpt_id() {
		return internal_dpt_id;
	}

	public void setInternal_dpt_id(int internal_dpt_id) {
		this.internal_dpt_id = internal_dpt_id;
	}

	public int getInternal_emp_id() {
		return internal_emp_id;
	}

	public void setInternal_emp_id(int internal_emp_id) {
		this.internal_emp_id = internal_emp_id;
	}

	public String getProblem_status() {
		return problem_status;
	}

	public void setProblem_status(String problem_status) {
		this.problem_status = problem_status;
	}

	public String getStype() {
		return stype;
	}

	public void setStype(String stype) {
		this.stype = stype;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProblem_context() {
		return problem_context;
	}

	public void setProblem_context(String problem_context) {
		this.problem_context = problem_context;
	}

	public int getHandle_dpt_id() {
		return handle_dpt_id;
	}

	public void setHandle_dpt_id(int handle_dpt_id) {
		this.handle_dpt_id = handle_dpt_id;
	}

	public int getHandle_emp_id() {
		return handle_emp_id;
	}

	public void setHandle_emp_id(int handle_emp_id) {
		this.handle_emp_id = handle_emp_id;
	}

	public int getCreate_dpt_id() {
		return create_dpt_id;
	}

	public void setCreate_dpt_id(int create_dpt_id) {
		this.create_dpt_id = create_dpt_id;
	}

	public int getCreate_emp_id() {
		return create_emp_id;
	}

	public void setCreate_emp_id(int create_emp_id) {
		this.create_emp_id = create_emp_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
}