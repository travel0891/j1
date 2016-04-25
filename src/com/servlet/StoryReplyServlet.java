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

public final class StoryReplyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public StoryReplyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("hello guest");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");
		request.setCharacterEncoding("utf-8");

		String action = request.getParameter("type");
		StringBuilder jsonString = null;
		if (action == null || action.isEmpty()) {
			jsonString = new StringBuilder();
			jsonString.append("{\"count\":0,\"story\":\"当前存在异常，请稍后重试\"}");
		} else {
			StoryReplyDao storyReplyDao = new StoryReplyDao();
			switch (action) {
			case "list":
				jsonString = new StringBuilder();
				try {
					List<StoryReply> listStoryReply = storyReplyDao
							.query(Integer.parseInt(request.getParameter("list_id")));

					jsonString = new StringBuilder();
					jsonString.append("{");
					jsonString.append("\"count\":" + listStoryReply.size());
					jsonString.append(",");
					jsonString.append("\"story\":[");
					for (int i = 0; i < listStoryReply.size(); i++) {
						jsonString.append(JSON.toJSONString(listStoryReply.get(i)));
						if (listStoryReply.size() > (i + 1)) {
							jsonString.append(",");
						}
					}
					jsonString.append("]");
					jsonString.append("}");
				} catch (NumberFormatException e1) {
					jsonString.append("{\"count\":0,\"story\":\"当前存在异常，稍后重试\"}");
					e1.printStackTrace();
				} catch (SQLException e1) {
					jsonString.append("{\"count\":0,\"story\":\"当前存在异常，稍后重试\"}");
					e1.printStackTrace();
				}
				break;
			case "add":
				jsonString = new StringBuilder();
				try {
					StoryReply tempModel = new StoryReply();
					tempModel.setList_id(Integer.parseInt(request.getParameter("list_id")));
					tempModel.setReply_text(request.getParameter("reply_text"));
					tempModel.setCreate_begin_id(Integer.parseInt(request.getParameter("begin_id")));
					if (storyReplyDao.add(tempModel)) {
						jsonString.append("{\"count\":1,\"story\":\"pass\"}");
					} else {
						jsonString.append("{\"count\":0,\"story\":\"提交失败，请重试\"}");
					}
				} catch (SQLException e) {
					jsonString.append("{\"count\":0,\"story\":\"当前存在异常，稍后重试\"}");
					e.printStackTrace();
				}
				break;
			case "del":
				jsonString = new StringBuilder();
				try {
					int getReply_id = Integer.parseInt(request.getParameter("reply_id"));
					if (storyReplyDao.del(getReply_id) == true) {
						jsonString.append("{\"count\":1,\"story\":\"pass\"}");
					} else {
						jsonString.append("{\"count\":0,\"story\":\"提交失败，请重试\"}");
					}
				} catch (SQLException e) {
					jsonString.append("{\"count\":0,\"story\":\"当前存在异常，请稍后重试\"}");
					e.printStackTrace();
				}
				break;
			default:
				jsonString = new StringBuilder();
				jsonString.append("{\"count\":0,\"story\":\"当前存在异常，请稍后重试\"}");
				break;
			}
		}
		response.getWriter().append(jsonString.toString());
	}
}
