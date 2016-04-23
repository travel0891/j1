package com.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.*;
import com.dao.*;

public final class StoryBeginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StoryBeginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("hello guest");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		String jsonString = null;

		StoryBeginDao storyBeginDao = new StoryBeginDao();

		StoryBegin tempModel = new StoryBegin();
		tempModel.setAccount(request.getParameter("storyAccount"));
		tempModel.setPasswrod(request.getParameter("storyPasswrod"));

		try {
			StoryBegin storyBeginModel = storyBeginDao.get(tempModel);
			if (storyBeginModel != null && storyBeginModel.getBegin_id() > 0) {
				jsonString = "{\"count\":1,\"story\":\"pass\"}";
			} else {
				jsonString = "{\"count\":0,\"story\":\"当前账号或密码错误\"}";
			}
		} catch (SQLException e) {
			jsonString = "{\"count\":0,\"story\":\"当前存在异常，请稍后重试\"}";
			e.printStackTrace();
		}
		response.getWriter().append(jsonString);
	}
}