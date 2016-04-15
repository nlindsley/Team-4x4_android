package com.tspandroid;
import java.sql.*;
import java.util.ArrayList;

public class DBHookUp {
	
	Connection conn = null;
	Statement stmt = null;
	int rowCount = 0;
	ResultSet rs = null;
	
	public int connectDB(){
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 1;
		}
		try {
			conn = DriverManager.getConnection("jdbc:sqlite:highscores.db");
		} catch (SQLException e) {
			e.printStackTrace();
			return 1;
		}
		return 0;	
	}
	
	public int disconnect(){
		try{
			conn.close();
		}catch(SQLException e){
			e.printStackTrace();
			return 1;
		}
		return 0;
	}
	
	/**
	 * This method adds all the scores to the database. Please call ensureHighestThree
	 * (see next method) to cut the database to the lightest possible weight.
	 * @param score
	 * @return 1 if successful, 0 if not; mostly as a debug.
	 */
	public int updateDB(int score){
		connectDB();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		try{
			stmt = conn.createStatement();
			rowCount = stmt.executeUpdate("insert into scores values("+score+")");
			if (rowCount !=1){
				conn.rollback();
				return 0;
			}
			else{
				conn.commit();
				return 1;
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		ensureHighestThree();
		disconnect();
		return 1;
	}
	
	/**
	 * This is not strictly necessary as the database should be lightweight enough to
	 * prevent worry about the size of the database. Nevertheless, to be as certain as
	 * possible, when the database is updated, all values that are not the highest three
	 * will be deleted to ensure that this is the lightest possible database. 
	 */
	public void ensureHighestThree(){
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from scores order by value desc");
			int counter = 0;
			while(rs.next()){
				int val = rs.getInt(1);
				counter +=1;
				if (counter>3){
					stmt.execute("delete from scores where value = "+val);
					counter -=1;
				}
				
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
	}
	/**
	 * If called, nothing will happen unless the table has been deleted, which should never happen.
	 * The commented lines shouldn't be called either.
	 */
	
	private void create (){
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate("create table if not exists scores(value int)");
			//stmt = conn.createStatement();
			//stmt.executeUpdate("delete from scores");
			//stmt = conn.createStatement();
			//stmt.executeUpdate("vacuum");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Integer> getScores(){
		connectDB();
		ArrayList<Integer> ret = new ArrayList<Integer>();
		try{
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from scores order by value desc");
			while(rs.next()){
				int val = rs.getInt(1);
				ret.add(val);
			}
		}catch(SQLException ex){
			ex.printStackTrace();
		}
		disconnect();
		return ret;
	}
	
	public DBHookUp(){
		this.connectDB();
		this.create();
		this.disconnect();
	}
	
	
	/**
	 * This is run from within the command line/Eclipse to do initialization. Not intended to be run solo.
	 */
	public static void main(String[] args){
		DBHookUp init = new DBHookUp();
		init.connectDB();
		init.create();
		init.updateDB(0);
		init.updateDB(0);
		init.updateDB(0);
		//int [] a = init.getScores();
		//for(int i:a){
		//	System.out.println(i);
		//}
		init.disconnect();
	}

}
