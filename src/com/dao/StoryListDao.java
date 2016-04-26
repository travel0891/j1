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

	public List<StoryList> query(int pageIndex, int pageSize, int begin_id) throws SQLException {

		List<StoryList> listStory = new ArrayList<StoryList>();
		StoryList storyList = null;

		SimpleDateFormat fmt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

		StringBuilder sbSQL = new StringBuilder();
		String stringTOP = pageSize > 0 ? " top " + pageSize : null;

		StringBuilder sbPAGE = new StringBuilder();
		StringBuilder sbWHERE = new StringBuilder();
		sbWHERE.append(" 1 = 1 ");

		pageIndex = pageIndex > 0 ? pageIndex - 1 : 0;
		if (pageIndex > 0) {
			sbPAGE.append(" and a.id < ");
			sbPAGE.append(" ( select min(id) from (select top " + (pageIndex * pageSize)
					+ " id from xps_problem with(nolock) where " + sbWHERE.toString()
					+ " order by id desc ) as dataList ) ");
		}

		sbSQL.append(" select " + stringTOP);
		sbSQL.append(
				" (select count(1) from xps_problem with(nolock) where " + sbWHERE.toString() + ") as list_count ");
		sbSQL.append(" ,a.id as list_id ");
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

		sbSQL.append(" where " + sbWHERE.toString() + sbPAGE.toString());
		sbSQL.append(" order by a.id desc ");

		Connection conn = dbquery.getConnection();
		PreparedStatement ptmt = conn.prepareStatement(sbSQL.toString());
		System.out.println(sbSQL.toString());

		ptmt.setInt(1, begin_id);

		ResultSet rs = ptmt.executeQuery();

		while (rs.next()) {
			storyList = new StoryList();
			storyList.setList_count(rs.getInt("list_count"));
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

	public StoryList get(int begin_id, int list_id) throws SQLException {

		StoryList storyList = null;

		SimpleDateFormat fmt = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

		Connection conn = dbquery.getConnection();

		StringBuilder sbSQL = new StringBuilder();
		sbSQL.append(" insert into xps_problem_counts(page_id,dpt_id,emp_id,create_time) ");
		sbSQL.append(" values ");
		sbSQL.append(" (? ,0 ,? ,getdate()); ");

		PreparedStatement ptmt1 = conn.prepareStatement(sbSQL.toString());
		System.out.println(sbSQL.toString());

		ptmt1.setInt(1, list_id);
		ptmt1.setInt(2, begin_id);

		ptmt1.execute();

		sbSQL = new StringBuilder();
		sbSQL.append(" select a.id as list_id ");
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

		sbSQL.append(" ,isnull(c.customer_type,'') as source_tag ");
		sbSQL.append(" ,isnull(d.emp_mobile,'') as source_tel ");
		sbSQL.append(" ,problem_context as story_context ");

		sbSQL.append(" from xps_problem as a with(nolock) ");
		sbSQL.append(" left join xps_problem_source as b with(nolock) on a.problem_source_id = b.id ");
		sbSQL.append(" left join xps_problem_customer as c with(nolock) on c.id = b.customer_id ");
		sbSQL.append(" left join xpr_emp as d with(nolock) on a.create_emp_id = d.id ");
		sbSQL.append(" left join xpr_emp as e with(nolock) on a.handle_emp_id = e.id ");

		sbSQL.append(" where a.id = ? ; ");

		PreparedStatement ptmt2 = conn.prepareStatement(sbSQL.toString());
		System.out.println(sbSQL.toString());

		ptmt2.setInt(1, begin_id);
		ptmt2.setInt(2, list_id);

		ResultSet rs = ptmt2.executeQuery();

		if (rs.next()) {
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

			storyList.setSource_tag(rs.getString("source_tag"));
			storyList.setSource_tel(rs.getString("source_tel"));
			storyList.setStory_context(rs.getString("story_context"));
		}
		rs.close();

		return storyList;
	}
}