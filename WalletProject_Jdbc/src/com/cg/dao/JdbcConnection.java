package com.cg.dao;

import java.sql.*;


public class JdbcConnection {
	  public static final String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	    public static final String USER = "hr";
	    public static final String PASS = "hr";
	    /**
	     * Get a connection to database
	     * @return Connection object
	     */
	    public static Connection getConnection()
	    {
	      try {
	         // DriverManager.registerDriver(new Driver());
	          return DriverManager.getConnection(URL, USER, PASS);
	      } catch (SQLException ex) {
	          throw new RuntimeException("Error connecting to the database", ex);
	      }
	    }

}
