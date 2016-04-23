package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.db.dbquery;
import com.model.*;

public class StoryListDao {

	public List<StoryList> query(int top, int begin_id) throws SQLException {

		List<StoryList> listStory = new ArrayList<StoryList>();
		StoryList storyList = null;
		
		SimpleDateFormat fmt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
		StringBuilder sbSQL = new StringBuilder();
		sbSQL.append(" select top " + top);
		sbSQL.append(" a.id as list_id ");
		sbSQL.append(" ,isnull(c.customer_name,'') as source_text1 ");
		sbSQL.append(" ,isnull(b.emp_name,'') as source_text2 ");
		sbSQL.append(" ,a.problem_status as state_text ");
		sbSQL.append(" ,a.stype as type_text ");
		sbSQL.append(" ,a.title as story_text ");
		sbSQL.append(" ,e.emp_name as handle_text ");
		sbSQL.append(" ,d.emp_name as create_text ");
		sbSQL.append(" ,d.row_id as create_user_photo ");
		sbSQL.append(" ,a.create_time ");
		sbSQL.append(" ,(select count(1) from xps_problem_counts with(nolock) where page_id = a.id) as read_count ");
		sbSQL.append(" ,(select count(1) from xps_problem_heel with(nolock) where problem_id = a.id) as reply_count ");
		sbSQL.append(
				" ,(select count(1) from xps_problem_counts with(nolock) where page_id = a.id and emp_id = ?) as has_read ");
		sbSQL.append(" from xps_problem as a with(nolock) ");
		sbSQL.append(" left join xps_problem_source as b with(nolock) on a.problem_source_id = b.id ");
		sbSQL.append(" left join xps_problem_customer as c with(nolock) on c.id = b.customer_id ");
		sbSQL.append(" left join xpr_emp as d with(nolock) on a.create_emp_id = d.id ");
		sbSQL.append(" left join xpr_emp as e with(nolock) on a.handle_emp_id = e.id ");

		sbSQL.append(" where 1 = 1 ");
		sbSQL.append(" order by a.create_time desc ");

		Connection conn = dbquery.getConnection();
		PreparedStatement ptmt = conn.prepareStatement(sbSQL.toString());
		ptmt.setInt(1, begin_id);

		ResultSet rs = ptmt.executeQuery();

		System.out.println(sbSQL.toString());

		while (rs.next()) {
			storyList = new StoryList();
			storyList.setList_id(rs.getInt("list_id"));
			storyList.setSource_text(rs.getString("source_text1") + "." + rs.getString("source_text2"));
			storyList.setState_text(rs.getString("state_text"));
			storyList.setType_text(rs.getString("type_text"));
			storyList.setStory_text(rs.getString("story_text"));
			storyList.setHandle_text(rs.getString("handle_text"));
			storyList.setCreate_text(rs.getString("create_text"));
			storyList.setCreate_user_photo(rs.getString("create_user_photo"));
			storyList.setCreate_time(fmt.format(rs.getTimestamp("create_time")));
			storyList.setRead_count(rs.getInt("read_count"));
			storyList.setReply_count(rs.getInt("reply_count"));
			storyList.setHas_read(rs.getInt("has_read"));

			listStory.add(storyList);
		}
		rs.close();

		return listStory;
	}
}