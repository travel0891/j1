package com.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
		tempModel.setPasswrod(
				sendGet("", "", "http://127.0.0.1/callBack.aspx", "key=" + request.getParameter("storyPasswrod")));
		try {
			StoryBegin storyBeginModel = storyBeginDao.get(tempModel);
			if (storyBeginModel != null && storyBeginModel.getBegin_id() > 0) {
				jsonString = "{\"count\":1,\"story\":" + storyBeginModel.getBegin_id() + "}";
			} else {
				jsonString = "{\"count\":0,\"story\":\"当前账号或密码错误\"}";
			}
		} catch (SQLException e) {
			jsonString = "{\"count\":0,\"story\":\"当前存在异常，请稍后重试\"}";
			e.printStackTrace();
		}
		response.getWriter().append(jsonString);
	}

	private static String sendGet(String validateUrl1, String validateUrl2, String url, String param) {
		String result = "";
		if (validateUrl1 == validateUrl2) {
			BufferedReader in = null;
			try {
				String urlNameString = url + "?" + param;
				URL realUrl = new URL(urlNameString);
				URLConnection connection = realUrl.openConnection();
				connection.setRequestProperty("accept", "*/*");
				connection.setRequestProperty("connection", "Keep-Alive");
				connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
				connection.connect();
				in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				String line;
				while ((line = in.readLine()) != null) {
					result += line;
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			} finally {
				try {
					if (in != null) {
						in.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return result;
	}
}