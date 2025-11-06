package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentDaoJDBC implements DepartmentDao {
	
	private Connection conn;
	
	public DepartmentDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Department obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Department obj) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletById(Integer id) {
		
	}

	@Override
	public Department findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT * " +
					"FROM DEPARTMENT " +
					"WHERE ID = ?");
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			if(rs.next()) {
				Department dept = new Department();
				dept.setId(rs.getInt("Id"));
				dept.setName(rs.getString("Name"));
				return dept;
			}
			return null;
		} 
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	@Override
	public List<Department> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement(
					"SELECT * " +
					"FROM DEPARTMENT ");
			rs = st.executeQuery();
			
			List<Department> departments = new ArrayList<>();
			
			while (rs.next()) {
				Department dept = instantiationDepartment(rs);
				departments.add(dept);
			}
			return departments;
		}
		catch (SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	private Department instantiationDepartment(ResultSet rs) throws SQLException {
		Department dept = new Department();
		dept.setId(rs.getInt("Id"));
		dept.setName(rs.getString("Name"));
		return dept;
	}

}
