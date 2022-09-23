package com.xml.demo.repository;

import java.sql.*;
import java.util.ArrayList;

import com.xml.demo.model.SystemModel;

public class Db {

	public Connection mysqlConnect() {
		try {
			String url = "jdbc:mysql://localhost:3306/java_application";
			String user = "java";
			String pass = "java";
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Conexao estabelecida");
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public SystemModel getById(String sysId, String domainId, String productId) {
		Db connection = new Db();
		Connection conn = connection.mysqlConnect();

		SystemModel dataModel = new SystemModel();

		String query = "SELECT * FROM SYSTEM_PREFERENCES_ATTRIBUTE_VALUE INNER JOIN SYSTEM_PREFERENCES ON SYSTEM_PREFERENCES_ATTRIBUTE_VALUE.system_preferences_id = SYSTEM_PREFERENCES.id  WHERE system_preferences_id = ? AND domain_id = ? AND product_id = ?";

		try {
			PreparedStatement statement = conn.prepareStatement(query);
			int cont = 1;
			statement.setString(cont++, sysId);
			statement.setString(cont++, domainId);
			statement.setString(cont++, productId);
			statement.executeQuery();

			ResultSet result = statement.getResultSet();
			while (result.next()) {
				dataModel.setSystemPreferencesId(result.getString("system_preferences_id"));
				dataModel.setDomainId(result.getString("domain_id"));
				dataModel.setUserId(result.getString("user_id"));
				dataModel.setProductId(result.getString("product_id"));
				dataModel.setAttributeKey(result.getString("attribute_key"));
				dataModel.setAttributeValue(result.getString("attribute_value"));
				dataModel.setAttribute(result.getString("attribute"));
			}

			result.close();
			statement.close();

			System.out.println("Query executada com sucesso");
			return dataModel;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return dataModel;
	}

	public boolean save(SystemModel data) {
		Db connection = new Db();
		Connection conn = connection.mysqlConnect();
	
		String query = "INSERT INTO SYSTEM_PREFERENCES_ATTRIBUTE_VALUE(system_preferences_id, domain_id, user_id, product_id, attribute_key, attribute_value) VALUES(?, ?, ?, ?, ?, ?)";

		try {
			PreparedStatement stm = conn.prepareStatement(query);
			int cont = 1;
			stm.setString(cont++, data.getSystemPreferencesId());
			stm.setString(cont++, data.getDomainId());
			stm.setString(cont++, data.getUserId());
			stm.setString(cont++, data.getProductId());
			stm.setString(cont++, data.getAttributeKey());
			stm.setString(cont++, data.getAttributeValue());
			stm.executeUpdate();
			stm.close();
			
			System.out.println("sucesso método save!");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public ArrayList<SystemModel> list(String domainId) {
		Db connection = new Db();
		Connection conn = connection.mysqlConnect();

		SystemModel dataModel = new SystemModel();
		ArrayList<SystemModel> objArray = new ArrayList<SystemModel>();

		String query = "SELECT * FROM SYSTEM_PREFERENCES_ATTRIBUTE_VALUE INNER JOIN SYSTEM_PREFERENCES ON SYSTEM_PREFERENCES_ATTRIBUTE_VALUE.system_preferences_id = SYSTEM_PREFERENCES.id WHERE domain_id = ?";

		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			stmt.setString(1, domainId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				dataModel.setSystemPreferencesId(rs.getString("system_preferences_id"));
				dataModel.setDomainId(rs.getString("domain_id"));
				dataModel.setUserId(rs.getString("user_id"));
				dataModel.setProductId(rs.getString("product_id"));
				dataModel.setAttributeKey(rs.getString("attribute_key"));
				dataModel.setAttributeValue(rs.getString("attribute_value"));
				dataModel.setAttribute(rs.getString("attribute"));
				objArray.add(dataModel);
			}
			return objArray;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return objArray;
	}

}

