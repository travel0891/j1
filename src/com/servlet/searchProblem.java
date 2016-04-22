package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.*;
import com.dao.*;

import com.alibaba.fastjson.JSON;

public class searchProblem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public searchProblem() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		problemListDao problemDao = new problemListDao();
		List<problemList> listProblem = null;

		StringBuilder jsonString = new StringBuilder();

		jsonString.append("{");
		jsonString.append("\"code\":200,");
		jsonString.append("\"story\":[");

		try {
			listProblem = problemDao.searchProblemList();
			for (problemList item : listProblem) {
				jsonString.append(JSON.toJSON(item) + ",");
			}
			jsonString.append("],");
			jsonString.append("\"count\":" + listProblem.size());
			jsonString.append("}");
			response.getWriter().append(jsonString.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}