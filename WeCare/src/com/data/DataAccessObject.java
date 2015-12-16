package com.data;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.apache.taglibs.standard.lang.jstl.ELException;

import com.controller.Registration;
import com.model.Comment;
import com.model.Event;
import com.model.Reset;
import com.model.Topic;
import com.model.User;

/**
 * <h1>We Care</h1>
 * The <code>DataAccessObject</code> class implements all database related methods
 * needed for We Care application.
 * 
 * @version 1.0
 * @since 2015-05-28
 */
public class DataAccessObject {
	/**
	 * This method is used to register a new user to the We Care web application. 
	 * Places a default user image for the registration process.
	 * 
	 * @param user An object of <code>com.model.User</code> class with the new user details.
	 * @throws Exception If the user-id already exists or registration failed.
	 */
	public static void save(User user) throws Exception {
		Connection conn = DBManager.getCon();

		PreparedStatement saveUser = conn.prepareStatement("insert into user values(?,?,?,?,?,?)");
		PreparedStatement savePhoto = conn.prepareStatement("insert into profilepic values(?,?)");
		FileInputStream fis = null;
		try { 
			saveUser.setString(1, user.getuID()); //if the user id already exists throws a mysql exception

			saveUser.setString(2, user.getPassword());
			saveUser.setString(3, user.getEmail());
			/*pst.setString(4, user.getSecurityQuestion());
			pst.setString(5, user.getSecurityAnswer());*/
			saveUser.setString(4, user.getName());
			saveUser.setString(5, user.getGender());
			saveUser.setString(6, user.getCity());

			//putting a default image into the user table;
			/*URL url = Object.getClass().getResource("user_img_default.png");
			System.out.println(url.getPath());
			File photo = new File(url.getPath());*/
			//System.out.println(Registration.realPath);
			File photo = new File(Registration.realPath);
			fis = new  FileInputStream(photo);
			savePhoto.setString(1, user.getuID());
			savePhoto.setBinaryStream(2, fis, photo.length());


			if(saveUser.executeUpdate() != 1) {
				throw new Exception("Registration failed.");
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("User ID already exists. Please try another.");
		} finally {
			DataAccessObject.cleanUp(saveUser, conn);
			savePhoto.close();
			fis.close();
		}

		//cleanup
		/*pst.close();
		if(conn != null) {
			conn.close();
		}*/

	}

	/**
	 * This method is used to get the registered user details.
	 * 
	 * @param uID The user-id for which the user details are required.
	 * @return <code>com.model.User</code> object with the details corresponding to the user-id passed to this method.
	 * @throws Exception If the user-id doesn't exist.
	 */
	public static User getUser(String uID) throws Exception {
		Connection conn = DBManager.getCon();
		PreparedStatement pst = conn.prepareStatement("Select * from user where userid=?");
			//System.out.println(uID);
		pst.setString(1, uID);
		ResultSet record = pst.executeQuery();

		User user;
		if(!record.next()) {
			throw new Exception("The entered user ID does not exist.");
		} else {
			user = new User();
			user.setuID(record.getString("userid"));
			user.setName(record.getString("username"));
			user.setPassword(record.getString("password"));
			user.setGender(record.getString("gender"));
			user.setCity(record.getString("city"));
			user.setEmail(record.getString("email"));
			/*user.setSecurityQuestion(record.getString("securityquestion"));
			user.setSecurityAnswer(record.getString("answer"));*/
			/*user.setPhoto(record.getBlob("photo"));*/
		}

		//cleanup
		/*pst.close();
		if(conn != null) {
			conn.close();
		}*/
		record.close();
		DataAccessObject.cleanUp(pst, conn);

		return user;
	}

	/**
	 * This method performs cleanup activities for the parameters passed. 
	 *
	 * @param pst A <code>java.sql.PreparedStatement</code> object.
	 * @param conn A <code>java.sql.Connection</code> object.
	 * @throws Exception On cleanup error.
	 */
	static void cleanUp(PreparedStatement pst, Connection conn) throws Exception {
		pst.close();
		if(conn != null) {
			conn.close();
		}
	}

	/**
	 * This method performs cleanup activities for the parameters passed.
	 * 
	 * @param conn A <code>java.sql.Connection</code> object.
	 * @throws Exception On cleanup error.
	 */
	static void cleanUp(Connection conn) throws Exception {
		if(conn != null) {
			conn.close();
		}
	}

	/**
	 * This method is used to save the <code>com.model.Topic</code> object into the 
	 * database when the user starts a new topic.
	 *  
	 * @param topic A <code>com.model.Topic</code> object.
	 * @throws Exception On database connection error.
	 */
	public static void saveTopic(Topic topic) throws Exception {
		//connecting to DB
		Connection conn = DBManager.getCon();

		//adding the topic to the DB
		PreparedStatement pst = conn.prepareStatement("insert into topics(topicName, comment, userId, time) values(?,?,?,?)");

		pst.setString(1, topic.getTopicName());
		pst.setString(2, topic.getComment());
		pst.setString(3, topic.getUID());
		pst.setTimestamp(4, new java.sql.Timestamp(topic.getDate().getTime()));

		if(pst.executeUpdate() != 1) {
			throw new Exception("Topic could not be saved at this time");
		}
	}


	/**
	 * This method is used to get a list of all the topics added by a 
	 * particular user.
	 * 
	 * @param user A <code>com.model.User</code> object of whom all the topics are to be listed.
	 * @return An array of <code>com.model.Topic</code> objects added by the specific user. 
	 * @throws Exception When no topics are found corresponding to that user.
	 */
	public static Topic[] getTopics(User user) throws Exception {
		//declarations
		ResultSet record;
		Topic topics[] = null;

		//connecting to DB
		Connection conn = DBManager.getCon();

		//adding the topic to the DB
		PreparedStatement pst = conn.prepareStatement("select * from topics where userId=? order by topicId desc");
		//System.out.println(user.getUID());
		pst.setString(1, user.getuID());
		record = pst.executeQuery();
		/*if(!record.next()) {
			throw new Exception("No topics found.");
		}*/

		/*try {*/
		int rows = 0;
		//getting the number of rows in ResultSet
		if(record.last()) {
			rows = record.getRow();
			//System.out.println(rows);
			//moving to the beginning of the ResultSet
			record.beforeFirst();
		}

		//System.out.println("getTopics rows: " + rows);

		if(rows == 0) throw new Exception("No topics found.");
		else {
			//System.out.println(rows);
			topics = new Topic[rows];
			/*System.out.println((topics == null));
					System.out.println((topics[0] == null));
					System.out.println((topics[1] == null));
					System.out.println(topics.length);*/
			int i = 0;
			while(record.next()) {
				topics[i] = new Topic();
				//System.out.println("test3");
				//System.out.println(record.getInt("topicId"));
				topics[i].setTopicID(record.getInt("topicId"));
				//System.out.println(record.getString("topicName"));
				topics[i].setTopicName(record.getString("topicName"));
				//System.out.println(record.getString("comment"));
				topics[i].setComment(record.getString("comment"));
				topics[i].setUID(record.getString("userId"));
				topics[i].setDate(new Date(record.getTimestamp("time").getTime()));
				i++;
				//System.out.println();
			}
		}
		/*System.out.println(topics);
				System.out.println(topics[0].getTopicName());*/

		/*} catch (Exception e) {
				System.out.println("test-exc");
			e.printStackTrace();
			throw new Exception("No topics found.");
		}*/

		if(record != null) record.close();

		DataAccessObject.cleanUp(pst, conn);

		return topics;
	}


	/**
	 * This method is used to delete a topic from the database.
	 * 
	 * @param topicID The topic id corresponding to the topic to be deleted.
	 * @throws Exception On database connection error.
	 */
	public static void deleteTopic(int topicID) throws Exception {
		Connection conn = DBManager.getCon();

		PreparedStatement pst = conn.prepareStatement("delete from topics where topicId=?");
		pst.setInt(1, topicID);
		if(pst.executeUpdate() != 1)
			throw new Exception("Topic could not be deleted.");
	}

	/**
	 * This method is used to get the details of a specific topic.
	 * 
	 * @param topicID The topic id corresponding to the topic of which the details are needed.
	 * @return An <code>com.model.Topic</code> object.
	 * @throws Exception If no topic is found with the given topic ID. 
	 */
	public static Topic getTopic(int topicID) throws Exception {
		Topic topic = null;

		Connection conn = DBManager.getCon();

		PreparedStatement pst = conn.prepareStatement("select * from topics where topicId=?");
		pst.setInt(1, topicID);
		ResultSet record = pst.executeQuery();

		if(!record.next()) throw new Exception("No record found!");
		else {
			topic = new Topic();
			topic.setTopicID(record.getInt("topicId"));
			topic.setTopicName(record.getString("topicName"));
			topic.setComment(record.getString("comment"));
			topic.setUID(record.getString("userId"));
			topic.setDate(new Date(record.getTimestamp("time").getTime()));
		}

		return topic;
	}

	/**
	 * This method is used to update/edit the author comment 
	 * of a topic added by that user.
	 *  
	 * @param topicID The topic id corresponding to the topic which is edited.
	 * @param newComment The new comment given by the user.
	 * @throws Exception On database connection error.
	 */
	public static void updateTopic(int topicID, String newComment) throws Exception {
		Connection conn = DBManager.getCon();

		PreparedStatement pst = conn.prepareStatement("update topics set comment=? where topicId=?");
		pst.setString(1, newComment);
		pst.setInt(2, topicID);

		if(pst.executeUpdate() != 1) {
			throw new Exception("The topic could not be edited.");
		}
	}

	/**
	 * This method is used to get a list of all the topics in
	 * the database till date. It retrieves all the topics added
	 * by all users.
	 * 
	 * @return An <code>java.util.ArrayList</code> object containing <code>com.model.Topic</code> objects.
	 * @throws Exception If no topics are found in the database.
	 */
	public static ArrayList<Topic> getTopics() throws Exception {
		ArrayList<Topic> topics = null;
		Topic topic = null;

		Connection conn = DBManager.getCon();

		PreparedStatement pst = conn.prepareStatement("select * from topics");
		ResultSet records = pst.executeQuery();

		if(!records.next()) throw new Exception("No topics found!");
		else {
			records.beforeFirst();
			/*int rows = 0;
			//getting the number of rows in ResultSet
			if(records.last()) {
				rows = records.getRow();
					//System.out.println(rows);
				//moving to the beginning of the ResultSet
				records.beforeFirst();
			}*/

			topics = new ArrayList<Topic>(); //creating an array of topics

			//adding topics to array
			/*int i = 0;*/
			while(records.next()) {
				topic = new Topic();
				topic.setTopicID(records.getInt("topicId"));
				topic.setTopicName(records.getString("topicName"));
				topic.setComment(records.getString("comment"));
				topic.setUID(records.getString("userId"));
				topic.setDate(
						new Date(records.getTimestamp("time").getTime()) //converting the SQL timestamp to Java Date class
						);
				topics.add(topic);
				/*System.out.println(topic.getTopicID());
					System.out.println(topics.get(i).getTopicName());*/
				/*i++;*/
			}

			/*System.out.println(topics);*/
		}

		if(records != null) records.close();
		cleanUp(pst, conn);

		return topics;
	}

	/**
	 * This method is used to get all the user comments specific to the topic selected.
	 * 
	 * @param topicID Topic ID corresponding to the topic of which the comments are to be retrieved.
	 * @return An <code>java.util.ArrayList</code> object containing <code>com.model.Comment</code> objects corresponding to the topic ID passed.
	 * @throws Exception On database connection error.
	 */
	public static ArrayList<Comment> getComments(int topicID) throws Exception {
		Comment comment = null;
		ArrayList<Comment> comments = new ArrayList<Comment>();

		//DB connection
		Connection conn = DBManager.getCon();

		PreparedStatement pst = conn.prepareStatement("select * from comments where topicId=? order by id desc");
		pst.setInt(1, topicID);
		ResultSet record = pst.executeQuery();

		/*if(!record.next()) throw new Exception("");*/
		while(record.next()) {
			comment = new Comment(
					record.getInt("id"),
					record.getInt("topicId"),
					record.getString("userID"),
					record.getString("comment"),
					new Date(record.getTimestamp("time").getTime())
					);

			comments.add(comment);
		}

		//cleanup
		if(record != null) record.close();
		cleanUp(pst, conn);

		return comments;
	}

	/**
	 * This method is used to add a user comment to the database. Contains
	 * the topic details to which the user has commented.
	 * 
	 * @param commentObj A <code>com.model.Comment</code> object containing the details of the user comment.
	 * @throws Exception On database connection error.
	 */
	public static void addUserComment(Comment commentObj) throws Exception {
		Connection conn = DBManager.getCon();
		PreparedStatement pst = conn.prepareStatement(
				"insert into comments(topicID, userId, comment , time) values(?,?,?,?)"
				);

		pst.setInt(1, commentObj.getTopicID());
		pst.setString(2, commentObj.getUserID());
		pst.setString(3, commentObj.getComment());
		pst.setTimestamp(4, new Timestamp(commentObj.getTime().getTime()));

		if(pst.executeUpdate() != 1) throw new Exception("The comment could not be added at this time.");

		//cleanup
		cleanUp(pst, conn);
	}

	/**
	 * This is method is used to change the password of a user.
	 * 
	 * @param uid The user id of which the password needs to be changed.
	 * @param newPass The new password.
	 * @throws Exception On database connection error.
	 */
	public static void updatePassword(String uid, String newPass) throws Exception {
		Connection conn = DBManager.getCon();

		PreparedStatement update = conn.prepareStatement("update user set password=? where userid=?");

		update.setString(1, newPass);
		update.setString(2, uid);

		if(update.executeUpdate() != 1) throw new Exception("Could not update password at this time. Please try again.");

		//cleanup
		cleanUp(update, conn);
	}

	/**
	 * This method is used to get the list of topics depending
	 * on what user is searching.
	 * 
	 * @param searchStr The search string.
	 * @return An <code>java.util.ArrayList</code> object containing <code>com.model.Topic</code> objects corresponding to the search.
	 * @throws Exception
	 */
	public static ArrayList<Topic> searchTopics(String searchStr) throws Exception {
		ArrayList<Topic> searchedTopics = null;
		Topic topic = null;

		//System.out.println("DAO");
		Connection conn = null;
		//System.out.println(conn);
		conn = DBManager.getCon();
		//System.out.println(conn);
		PreparedStatement pst = conn.prepareStatement("select * from topics where topicName like ?");

		searchStr = "%" + searchStr + "%";
		pst.setString(1, searchStr.trim());

		ResultSet records = pst.executeQuery();
		//System.out.println(records.next());
		if(records.next()) {
			//System.out.println("if");
			searchedTopics = new ArrayList<Topic>();
			records.beforeFirst();
		} else {
			//System.out.println("exception");
			throw new Exception("No similar topics found!");
		}
		while(records.next()) {
			topic = new Topic();
			topic.setTopicID(records.getInt("topicId"));
			topic.setTopicName(records.getString("topicName"));
			topic.setComment(records.getString("comment"));
			topic.setUID(records.getString("userId"));
			topic.setDate(
					new Date(records.getTimestamp("time").getTime()) //converting the SQL timestamp to Java Date class
					);
			searchedTopics.add(topic);
			//System.out.println(searchedTopics);
		}

		//System.out.println(searchedTopics);

		if(records != null) records.close();
		cleanUp(pst, conn);

		return searchedTopics;
	}

	/**
	 * This method is used to get a list of the events yet to come.
	 * 
	 * @param limit A limit specification on the number of events to be retrieved. <em>Zero</em> (0) if all the events are needed.
	 * @return An <code>java.util.ArrayList</code> object containing <code>com.model.Event</code> objects whose number depends on the limit specified.
	 * @throws Exception If no events are found after current date.
	 */
	public static ArrayList<Event> getEvents(int limit) throws Exception {
		ArrayList<Event> events = null;
		Event event = null;
		String query;

		Connection conn = DBManager.getCon();
		if(limit == 0) {
			query = "select * from events order by id desc";
		} else {
			query = "select * from events where time >= now() order by time asc limit " + limit;
		}
		PreparedStatement pst = conn.prepareStatement(query);
		ResultSet record = pst.executeQuery();

		if(!record.next()) {
			throw new ELException("No events are going on!");
		} else {
			record.beforeFirst();
			events = new ArrayList<Event>();

			while(record.next()) {
				event = new Event();
				event.setId(record.getInt("id"));
				event.setName(record.getString("name"));
				event.setTime(new Date(record.getTimestamp("time").getTime()));
				event.setVenue(record.getString("venue"));
				events.add(event);
			}
		}

		if(record != null) record.close();
		cleanUp(pst, conn);

		return events;
	}

	/**
	 * This method is used to save an event into the database.
	 * 
	 * @param event A <code>com.model.Event</code> object with all the event details to be added.
	 * @throws Exception On database connection error.
	 */
	public static void saveEvent(Event event) throws Exception {

		Connection conn = DBManager.getCon();
		PreparedStatement pst = conn.prepareStatement("insert into events(name,description,time,venue) values(?,?,?,?)");

		pst.setString(1, event.getName());
		pst.setString(2, event.getDescription());
		pst.setString(4, event.getVenue());
		pst.setTimestamp(3, new Timestamp(event.getTime().getTime()));

		if(pst.executeUpdate() != 1) {
			throw new Exception("Event could not be added at this time");
		}

		cleanUp(pst, conn);

	}

	/**
	 * This method is used to delete an event from the database.
	 * 
	 * @param id The event id of the event which needs to be deleted.
	 * @throws Exception On database connection error.
	 */
	public static void deleteEvent(int id) throws Exception {
		Connection conn = DBManager.getCon();
		PreparedStatement pst = conn.prepareStatement("delete from events where id=?");

		pst.setInt(1, id);
		if(pst.executeUpdate() != 1) {
			throw new Exception("The event could not be deleted at this time.");
		}

		cleanUp(pst, conn);
	}

	/**
	 * This method is used to get the details of an event corresponding 
	 * to the event id passed.
	 * 
	 * @param id The event id corresponding the event of which the details are needed.
	 * @return An <code>com.model.Event</code> object with details corresponding to the event id passed.
	 * @throws Exception If the event is not found.
	 */
	public static Event getEvent(int id) throws Exception {
		Connection conn = DBManager.getCon();
		PreparedStatement pst = conn.prepareStatement("select * from events where id=?");
		pst.setInt(1, id);

		ResultSet record = pst.executeQuery();
		Event event = null;

		if(record.next()) {
			event = new Event();
			event.setName(record.getString("name"));
			event.setDescription(record.getString("description"));
			event.setId(record.getInt("id"));
			event.setVenue(record.getString("venue"));
			event.setTime(new Date(record.getTimestamp("time").getTime()));
		} else {
			throw new Exception("Cannot find the event");
		}

		record.close();
		cleanUp(pst, conn);

		return event;

	}
	
	
	/**
	 * This method is used to generate a unique token for each user and return it.
	 * 
	 * @param resetUID The user-id for which the reset token needs to be generated.
	 * @return The token generated
	 * @throws Exception If server could not generate the token.
	 */
	public static int getResetToken(String resetUID) throws Exception {
		Connection conn = DBManager.getCon();
		PreparedStatement pst = conn.prepareStatement("insert into reset(userid,resettime) values(?,?)");
		pst.setString(1, resetUID);
		Timestamp ts = new java.sql.Timestamp(new Date().getTime());
		pst.setTimestamp(2, ts);

		if(pst.executeUpdate() != 1) {
			throw new Exception("Server is busy please try again after some time.");
		}

		PreparedStatement getToken = conn.prepareStatement("select token from reset where userid=? and resettime=?");
		getToken.setString(1, resetUID);
		getToken.setTimestamp(2, ts);
		/*needs two keys to get unique entry*/

		int token = -1;
		try {
			ResultSet record = getToken.executeQuery();
			if(record.next()) {
				token = record.getInt(1);
			}
			return token;
		} catch(Exception e) {
			e.printStackTrace();
			throw new Exception("Server is busy please try again after some time.");
		}
	}
	
	/**
	 * This method is used to get the reset details based on the token number passed.
	 * 
	 * @param token The reset token.
	 * @return <code>com.model.Reset</code> object with the reset details corresponding to the token passed to this method.
	 * @throws Exception If the token is invalid.
	 */
	public static Reset getResetDetails(int token) throws Exception {
		Connection conn = DBManager.getCon();
		PreparedStatement getDetails = conn.prepareStatement("Select * from reset where token=?");
			//System.out.println(uID);
		getDetails.setInt(1, token);
		ResultSet record = getDetails.executeQuery();

		Reset resetObj;
		if(!record.next()) {
			throw new Exception();//"The link has already been used before. Please generate a new one if required.");
		} else {
			resetObj = new Reset();
			resetObj.setToken(token);
			resetObj.setUid(record.getString(2));
			resetObj.setResetTime(new Date(record.getTimestamp(3).getTime()));
		}
		
		PreparedStatement deleteResetRow = conn.prepareStatement("delete from reset where token=?");
		deleteResetRow.setInt(1, token);
		if(deleteResetRow.executeUpdate() != 1) {
			throw new Exception("Server busy!");
		}

		//cleanup
		/*pst.close();
		if(conn != null) {
			conn.close();
		}*/
		record.close();
		DataAccessObject.cleanUp(getDetails, conn);

		return resetObj;
	}
}//DAO
