package com.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.*;
import com.db.*;

public class problemListDao {

	public List<problemList> searchProblemList() throws SQLException {

		String sql = " select top 10 id,problem_status,title,create_time from xps_problem order by create_time desc ";

		Connection conn = dbquery.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		List<problemList> listProblem = new ArrayList<problemList>();
		problemList problem = null;

		while (rs.next()) {
			problem = new problemList();
			problem.setId(rs.getInt("id"));
			problem.setProblem_status(rs.getString("problem_status"));
			problem.setTitle(rs.getString("title"));
			problem.setCreate_time(rs.getDate("create_time"));

			listProblem.add(problem);
		}
		rs.close();
		return listProblem;
	}
}
