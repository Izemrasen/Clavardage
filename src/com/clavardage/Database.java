package com.clavardage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
	public static Connection connect()
	{
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:/home/guilhem/clavardage.sqlite");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}
}
