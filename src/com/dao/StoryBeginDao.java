package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.dbquery;
import com.model.*;

public class StoryBeginDao {

	public StoryBegin get(StoryBegin model) throws SQLException {

		StoryBegin storyBegin = null;

		StringBuilder sbSQL = new StringBuilder();
		sbSQL.append(" select a.id as begin_id,a.emp_name as name_text,a.row_id as photo_url ");
		sbSQL.append(" from xpr_emp as a with(nolock) ");
		sbSQL.append(" join xpr_emp_record as b with(nolock) on a.id=b.emp_id ");
		sbSQL.append(" where b.emp_dpt_status = 1 ");
		sbSQL.append(" and a.emp_user = ? and a.emp_pwd = ? ");

		Connection conn = dbquery.getConnection();
		PreparedStatement ptmt = conn.prepareStatement(sbSQL.toString());
		System.out.println(sbSQL.toString());

		ptmt.setString(1, model.getAccount());
		ptmt.setString(2, model.getPasswrod());
		ResultSet rs = ptmt.executeQuery();

		if (rs.next()) {
			storyBegin = new StoryBegin();
			storyBegin.setBegin_id(rs.getInt("begin_id"));
			storyBegin.setAccount(model.getAccount());
			storyBegin.setPasswrod(model.getPasswrod());
			storyBegin.setName_text(rs.getString("name_text"));
			storyBegin.setPhoto_url(rs.getString("photo_url"));
		}
		rs.close();

		return storyBegin;
	}
}