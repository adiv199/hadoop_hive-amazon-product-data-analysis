
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;
import java.util.ArrayList;

import com.mysql.jdbc.log.Slf4JLogger;
 
public class HiveJdbcClient {
  private static String driverName = "org.apache.hive.jdbc.HiveDriver";
 
  /**
   * @param args
   * @throws SQLException
   */
  public Connection connectToHive() {
      try {
      Class.forName(driverName);
      
    		  } 
      catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    //replace "hive" here with the name of the user the queries should run as
    Connection con = null;
	try {
		con = DriverManager.getConnection("jdbc:hive2://localhost:10000/project", "hive", "cloudera");
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return con;
    /*String tableName = "testHiveDriverTable";
    //stmt.execute("drop table if exists " + tableName);
    //stmt.execute("create table " + tableName + " (key int, value string)");
    // show tables
    String sql = "";
    System.out.println("Running: " + sql);
    ResultSet res = stmt.executeQuery(sql);
    while (res.next()) {
      System.out.println(res.getString(1));
    }*/
       // describe table
  /*  sql = "describe " + tableName;
    System.out.println("Running: " + sql);
    res = stmt.executeQuery(sql);
    while (res.next()) {
      System.out.println(res.getString(1) + "\t" + res.getString(2));
    }
 
    // load data into table
    // NOTE: filepath has to be local to the hive server
    // NOTE: /tmp/a.txt is a ctrl-A separated file with two fields per line
    String filepath = "/tmp/a.txt";
    sql = "load data local inpath '" + filepath + "' into table " + tableName;
    System.out.println("Running: " + sql);
    stmt.execute(sql);
 
    // select * query
    sql = "select * from " + tableName;
    System.out.println("Running: " + sql);
    res = stmt.executeQuery(sql);
    while (res.next()) {
      System.out.println(String.valueOf(res.getInt(1)) + "\t" + res.getString(2));
    }*/
 
    // regular hive query
    
  }
  
  public ArrayList<ProductBean> checkReviewer(ReviewerBean r)
  {
	  String sql = "select asin from project.sports_by_year where reviewerid='" +r.getReviewerid()+"'";
	    System.out.println("Running: " + sql);
	    ArrayList<ProductBean> ar = new ArrayList<ProductBean>();
	    Statement stmt;
		try {
			stmt = connectToHive().createStatement();
			stmt.execute("add jar /home/cloudera/Project/json-serde-1.3.6-SNAPSHOT-jar-with-dependencies.jar");
			 ResultSet res = stmt.executeQuery(sql);
			    while (res.next()) {
			    	ProductBean p = new ProductBean();
			    	p.setAsin(res.getString(1));
			     // System.out.println(res.getString(1));
			      ar.add(p);
			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   return ar;
  }
  
  public ArrayList<String> getProductBoughtTogether(ProductBean jdbcPb)
  {
	  
	  String sql = "select explode(bought_together) from project.sport_items_bought_with_me where asin='"+jdbcPb.getAsin()+"' limit 5";
	  //  System.out.println("Running: " + sql);
	    ArrayList<String> ar = new ArrayList<String>();
	    Statement stmt;
		try {
			stmt = connectToHive().createStatement();
			 ResultSet res = stmt.executeQuery(sql);
			    while (res.next()) {
			    	ar.add(res.getString(1));
			     // System.out.println(res.getString(1));
			      connectToHive().close();
			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   return ar;
  }
  
  public ArrayList<String> getProductViewedTogether(ProductBean jdbcPb)
  {
	  
	  String sql = "select explode(also_viewed) from project.sport_items_viewed_with_me where asin='"+jdbcPb.getAsin()+"' limit 5";
	  //  System.out.println("Running: " + sql);
	    ArrayList<String> ar = new ArrayList<String>();
	    Statement stmt;
		try {
			stmt = connectToHive().createStatement();
			 ResultSet res = stmt.executeQuery(sql);
			    while (res.next()) {
			    	ar.add(res.getString(1));
			     // System.out.println(res.getString(1));
			      connectToHive().close();
			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   return ar;
  }
  
  public ArrayList<String> getProductAlsoBoughtTogether(ProductBean jdbcPb)
  {
	  
	  String sql = "select explode(also_bought) from project.sports_items_also_bought_with_me where asin='"+jdbcPb.getAsin()+"' limit 5";
	  //  System.out.println("Running: " + sql);
	    ArrayList<String> ar = new ArrayList<String>();
	    Statement stmt;
		try {
			stmt = connectToHive().createStatement();
			 ResultSet res = stmt.executeQuery(sql);
			    while (res.next()) {
			    	ar.add(res.getString(1));
			     // System.out.println(res.getString(1));
			      connectToHive().close();
			    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	   return ar;
  }
}
