package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.db.dbquery;
import com.model.StoryReply;

public class StoryReplyDao {

	public List<StoryReply> query(int list_id) throws SQLException {

		List<StoryReply> listStoryReply = new ArrayList<StoryReply>();
		StoryReply storyReplyModel = null;

		SimpleDateFormat fmt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		StringBuilder sbSQL = new StringBuilder();
		sbSQL.append(" select id as reply_id ");
		sbSQL.append(" ,context as reply_text ");
		sbSQL.append(" ,create_emp_id as create_begin_id");
		sbSQL.append(" ,(select emp_name from xpr_emp where id = create_emp_id) as create_begin_text ");
		sbSQL.append(" ,(select row_id from xpr_emp where id = create_emp_id) as create_begin_photo ");
		sbSQL.append(" ,create_time ");
		sbSQL.append(" from xps_problem_heel ");
		sbSQL.append(" where problem_id = ? ");
		sbSQL.append(" order by create_time asc; ");

		Connection conn = dbquery.getConnection();
		PreparedStatement ptmt = conn.prepareStatement(sbSQL.toString());
		ptmt.setInt(1, list_id);

		ResultSet rs = ptmt.executeQuery();

		System.out.println(sbSQL.toString());

		while (rs.next()) {
			storyReplyModel = new StoryReply();

			storyReplyModel.setReply_id(rs.getInt("reply_id"));
			storyReplyModel.setList_id(list_id);
			storyReplyModel.setReply_text(rs.getString("reply_text"));
			storyReplyModel.setCreate_begin_id(rs.getInt("create_begin_id"));
			storyReplyModel.setCreate_begin_text(rs.getString("create_begin_text"));
			storyReplyModel.setCreate_begin_photo(rs.getString("create_begin_photo"));
			storyReplyModel.setCreate_time(fmt.format(rs.getTimestamp("create_time")));

			listStoryReply.add(storyReplyModel);
		}
		rs.close();
		return listStoryReply;
	}

	public boolean add(StoryReply model) throws SQLException {

		StringBuilder sbSQL = new StringBuilder();
		sbSQL.append(" insert into xps_problem_heel(problem_id,context,create_dpt_id,create_emp_id,create_time) ");
		sbSQL.append(" values ");
		sbSQL.append(" (? ,? ,0 ,? ,getdate()); ");

		Connection conn = dbquery.getConnection();
		PreparedStatement ptmt = conn.prepareStatement(sbSQL.toString());
		ptmt.setInt(1, model.getList_id());
		ptmt.setString(2, model.getReply_text());
		ptmt.setInt(3, model.getCreate_begin_id());

		System.out.println(sbSQL.toString());

		return ptmt.executeUpdate() > 0 ? true : false;
	}

	public boolean del(int reply_id) throws SQLException {

		String sbSQL = " delete from xps_problem_heel where id = ?; ";

		Connection conn = dbquery.getConnection();
		PreparedStatement ptmt = conn.prepareStatement(sbSQL);
		ptmt.setInt(1, reply_id);

		System.out.println(sbSQL.toString());

		return ptmt.executeUpdate() > 0 ? true : false;
	}
}