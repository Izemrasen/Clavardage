package com.clavardage;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.clavardage.Message.Direction;
import com.clavardage.Message.Type;

public class History
{
	private ArrayList<Message<?>> messages;
	private User remoteUser; // TODO: put it elsewhere (session) or link History w/ Session

	public History(User remoteUser)
	{
		this.messages = new ArrayList<Message<?>>();
		this.remoteUser = remoteUser;
	}

	public ArrayList<Message<?>> getMessages()
	{
		return messages;
	}

	public void add(Message<?> m)
	{
		this.messages.add(m);
	}

	public User getRemoteUser()
	{
		return remoteUser;
	}

	// Load history from database for the specified remote user
	public void load()
	{
		// Connect to database
		Connection connection = Database.connect();
		if (connection == null)
			return;

		// Select messages exchanged with the user 
		try {
			Statement statement = connection.createStatement();
			String SQLRequest = "SELECT type, direction, date, content FROM message WHERE username = \"" + remoteUser.getUsername() + "\";";
			ResultSet results = statement.executeQuery(SQLRequest);

			// Loop through results
			ArrayList<Message<?>> messages = remoteUser.getHistory().messages;
			messages.clear();
			while (results.next()) {
				Type type = Message.types.get(results.getInt(1));
				Direction direction = Message.directions.get(results.getInt(2));
				Date timestamp = new Date(results.getLong(3) * 1000); // Seconds to milliseconds
				byte[] content = results.getBytes(4);

				// Forge message
				// MessageBlob prevents loss of information (instantiating Message is not possible due to its abstract type)
				MessageBlob message = new MessageBlob(type, direction, timestamp, content);
				System.out.println(message.toString());
				messages.add(message);
			}

			// Close database
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Save (append) history to database for the specified remote user
	public void save()
	{
		// Connect to database
		Connection connection = Database.connect();
		String SQLRequest = "INSERT INTO user VALUES(\"" + remoteUser.getUsername() + "\");";
		Statement statement = null;
		try {
			statement = connection.createStatement();

			// Insert user in case it doesn't exist
			statement.executeUpdate(SQLRequest);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// Insert messages
			for (Message<?> message : messages) {
				SQLRequest = "INSERT INTO message VALUES(\"" + remoteUser.getUsername() + "\", " + Message.types.indexOf(message.getType()) + ", " + Message.directions.indexOf(message.getDirection()) + ", " + message.getTimestamp().getTime() / 1000 + ", \"" + message.getContent() + "\");";
				System.out.println("<<<REQ: " + SQLRequest);
				try {
					statement.executeUpdate(SQLRequest);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public String toString()
	{
		String serializedHistory = "";
		boolean first = true;
		for (Message<?> m : messages) {
			if (!first)
				serializedHistory += "\n";
			else
				first = false;
			serializedHistory += "<";
			serializedHistory += m.getDirection() == Message.Direction.RECEIVED ? this.remoteUser.getUsername() : Main.getUsername();
			serializedHistory += ">\t";
			SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			serializedHistory += dt.format(m.getTimestamp()) + "\t" + m.toString();
		}
		return serializedHistory;
	}
}
