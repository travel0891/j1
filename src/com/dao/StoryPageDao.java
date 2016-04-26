package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.db.dbquery;
import com.model.*;

public class StoryPageDao {

	public List<StoryPage> query(int list_id) throws SQLException {

		List<StoryPage> listStoryPage = new ArrayList<StoryPage>();
		StoryPage storyPage = null;

		StringBuilder sbSQL = new StringBuilder();
		sbSQL.append(" select distinct 1 as page_id ");
		sbSQL.append(" ,page_id as list_id ");
		sbSQL.append(" ,(select emp_name from xpr_emp where id = emp_id) as page_user_text ");
		sbSQL.append(" ,(select row_id from xpr_emp where id = emp_id) as page_user_photo ");
		sbSQL.append(" from xps_problem_counts ");
		sbSQL.append(" where page_id > 0 and page_id = ? ");

		Connection conn = dbquery.getConnection();
		PreparedStatement ptmt = conn.prepareStatement(sbSQL.toString());
		System.out.println(sbSQL.toString());
		ptmt.setInt(1, list_id);

		ResultSet rs = ptmt.executeQuery();

		while (rs.next()) {
			storyPage = new StoryPage();
			storyPage.setPage_id(rs.getInt("page_id"));
			storyPage.setList_id(rs.getInt("list_id"));
			storyPage.setPage_user_text(rs.getString("page_user_text"));
			storyPage.setPage_user_photo(rs.getString("page_user_photo"));
			listStoryPage.add(storyPage);
		}
		rs.close();

		return listStoryPage;
	}
}
