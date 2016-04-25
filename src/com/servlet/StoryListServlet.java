package com.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.*;
import com.alibaba.fastjson.JSON;
import com.dao.*;

public class StoryListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StoryListServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("hello guest");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		String action = request.getParameter("type");
		String begin_id = request.getParameter("begin_id");
		String list_id = request.getParameter("list_id");
		String page_index = request.getParameter("page_index");

		StringBuilder jsonString = null;
		if (action == null || action.isEmpty()) {
			StoryListDao storyListDao = new StoryListDao();
			try {
				List<StoryList> listStoryList = storyListDao.query(Integer.parseInt(page_index), 20,
						Integer.parseInt(begin_id));
				jsonString = new StringBuilder();
				jsonString.append("{");
				jsonString.append("\"count\":" + listStoryList.get(0).getList_count());
				jsonString.append(",");
				jsonString.append("\"story\":[");
				for (int i = 0; i < listStoryList.size(); i++) {
					jsonString.append(JSON.toJSONString(listStoryList.get(i)));
					if (listStoryList.size() > (i + 1)) {
						jsonString.append(",");
					}
				}
				jsonString.append("]");
				jsonString.append("}");
			} catch (SQLException e) {
				jsonString = new StringBuilder();
				jsonString.append("{\"count\":0,\"story\":\"当前存在异常，请稍后重试\"}");
				e.printStackTrace();
			}
		} else {

			StoryListDao storyListDao = new StoryListDao();
			try {
				StoryList storyList = storyListDao.get(Integer.parseInt(begin_id), Integer.parseInt(list_id));
				List<StoryPage> listStoryPage = new StoryPageDao().query(storyList.getList_id());

				jsonString = new StringBuilder();
				jsonString.append("{");
				jsonString.append("\"count\":1");
				jsonString.append(",");
				jsonString.append("\"story\":[");
				jsonString.append(JSON.toJSONString(storyList));
				jsonString.append("]");
				jsonString.append(",");
				jsonString.append("\"page\":[");
				for (int i = 0; i < listStoryPage.size(); i++) {
					jsonString.append(JSON.toJSONString(listStoryPage.get(i)));
					if (listStoryPage.size() > (i + 1)) {
						jsonString.append(",");
					}
				}
				jsonString.append("]");
				jsonString.append("}");
			} catch (SQLException e) {
				jsonString = new StringBuilder();
				jsonString.append("{\"count\":0,\"story\":\"当前存在异常，请稍后重试\"}");
				e.printStackTrace();
			}

		}
		response.getWriter().append(jsonString.toString());
	}
}