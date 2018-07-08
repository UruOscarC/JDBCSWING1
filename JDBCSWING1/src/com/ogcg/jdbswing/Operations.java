package com.ogcg.jdbswing;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Operations {
	
	private static final String tableName = "producto";
	private static final String select = 
			"SELECT *" + "from " + tableName ;
			
	public void addProduct(Connection conn, int id, String description) {
		Statement stmt = null;
	    String query = "INSERT INTO " + tableName + "(id, description) VALUES(" 
	    		+ id + ",'" + description + "')";
	    try {
	        stmt = conn.createStatement();
	        boolean b =  stmt.execute(query);
	        if(!b) {
	        	System.out.println("Insertion success: \n" + id + ": " + description);
	        }
	        else {
	        	System.out.println("Insertion Error");
	        }
	    } catch (SQLException e ) {
	        System.out.println(e);
	    } finally {
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	}
	
	public void printProducts(Connection conn) {
		Statement stmt = null;
		ResultSet rs = null;
	    String query = select;
	    try {
	        stmt = conn.createStatement();
	        rs = stmt.executeQuery(query);
	        while (rs.next()) {
	        	Integer id = rs.getInt("id");
	            String description = rs.getString("description");
	            System.out.println(id + ": " + description);
	        }
	    } catch (SQLException e ) {
	        System.out.println(e);
	    } finally {
	    	try { if (rs != null) rs.close(); } catch (Exception e) {};
	        if (stmt != null) { try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} }
	    }
	}
}
