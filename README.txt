TODO
History
	Create tables if not existing
	Mv "/home/guilhem/*.sqlite" to relative path within project
GUI
	Link with the rest
		Authentication.login(): username form
		User.getActiveUsers(): display active users
		Server.Listen(): update GUI whenever receiving a message
		Session.send(): update GUI whenever sending a message
Message
	New structure:
		MessageData (not abstract): content type is abstract (String, byte[], etc.)
		MessageEvent (not abstract): same as now
		=> no Message anymore
	Move Network.broadcast() and Session.send() to Message
		-> Message.send(User remoteUser)
				-> if session not initiated: create it
				-> else get session.oos and write object to stream
		-> no need for message.label() when broadcasting
Network
	Fill a table with active users as they declare themselves (=> no servers for now)
Tests
	Try/catch: develop (e.g. java.net.ConnectException not caught)
	Class Test: supports CLI args (cf. lib org.apache.commons.cli)
	Mockito, etc.
	=> unit tests!!!
	Prevent null pointer exceptions (add many tests "if (xxx == null) return;") etc.



OPTIMIZATION/EXTENDED FEATURES
History
	use receive buffer: do not store the entire history in memory
		msg received -> update DB && display -> flush buffer
ArrayList -> Array (more efficient?)
Session
	check redundancies in table 'sessions' (sessions can be opened by server and by client on the same side!)
GUI
	Save username and write it by default in the login form
Correct warnings
Add @Override whenever needed
Assess quality of connection (e.g. ping or TCP information (window size, etc.))
	(dateSent, dateReceived) useless for this task because the two machines aren't necessarily sync to the same clock
Security
	Authentication (certificates), integrity (signature)
	Username not used anymore as a way to id users



CHOICES
Network stream
	#1. Library for Java objects serialization
		ObjectOutputStream, ObjectInputStream
		http://www.java2s.com/Tutorial/Java/0180__File/ReadingBasicDataFromanObjectStream.htm
	#2. Homemade: Message -> frame (e.g.: ID:4B CONTENT)-> ~~~~~~~ -> frame -> Message

Network infrastructure
				 TCP (over SSL?) / UDP (announcements)
	App1 (LAN1) -> ~~~~~~~~~~ -> App2 (LAN1)
						|
						|
					Server? (e.g. to centralize list of active users)

DB
	#1. SQLite: cool (single table, clean)
		Table
			History(remoteUsername, msgType/marker, timestamp, content, bool sent/received)
	#2. JSON/XML (dirtier but easier: one file per conversation?)
	
SQLITE
PRAGMA foreign_keys = ON;
DROP TABLE user;
DROP TABLE message;
CREATE TABLE user(name TEXT PRIMARY KEY) WITHOUT ROWID;
INSERT INTO user VALUES("michou");
INSERT INTO user VALUES("louis-yvain");
CREATE TABLE message(
	username TEXT, type INTEGER, direction INTEGER, date INTEGER, content BLOB,
	FOREIGN KEY(username) REFERENCES user(name)
);
INSERT INTO message VALUES("michou", 0, 0, 1512770405, "test");
INSERT INTO message VALUES("michou", 0, 1, 1512770405, 45);
SELECT * FROM message;


Message layout
	UDP datagrams
		|ID	|Data (null-separated)	|
		|1B	|<128B					|
		ID = {0x0 (announcement), 0x1 (username changed), etc.}
		Examples
			0x0 "michou"				<->	"Hi! I'm michou!"
			0x1 "michou" 0x0 "giacomo"	<->	"Hi! I was michou, now I'm giacomo!"
		
Network class
	Thread1: listening (TCP port, any addr, etc.)
	Thread2: waiting for user action


Complex problem
I want 2 contraints:
#1. Polymorphism: from a generic Message<?>, I want behaviours depending on the real subclass the object belongs to. E.g.:
	Message<?> m;
	m.toBytes(); // must behave differently considering the true type of Message (i.e. MessageText or MessageEvent)
	=> Solutions:
		#1. Message as interface => .toDatagram() implemented in subclasses
		#2. Message as abstract class => .toDatagram() implemented in subclasses
#2. I want to be able to forge any type of Message from DB
	=> Solutions:
		#1. Instantiate Message<?> (specify type, content, etc.)
			=> contradiction w/ constraint #1 (not possible to implement abstract class or interface)
		#2. Instantiate the right Message subclass (Text, Event) according to Message.type
			switch (type) { case TEXT: new MessageText ; case DATA : new MessageData...}
		#3. Hack: instantiate MessageText but change its type with the desired type
		#4. Instantiate an alternative class (e.g. MessageForged)