package pl.codete.database;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
    private String driver = "jdbc:postgresql";
    private String host = "michalburmz5.nazwa.pl:5432";
    private String database = "Codete";
    private String userTable = "postgres";
    private String password = "Wojna2ds";
        
	public Connection GetConnection() throws Exception {
      try{
        String connectionURL = driver + "://" + host + "/" + database;
        Connection connection = null;
        Class.forName("org.postgresql.Driver").newInstance();
        connection = DriverManager.getConnection(connectionURL, userTable, password);
        return connection;
      }
      catch (SQLException e){
        throw e;	
      }
      catch (Exception e){
        throw e;	
      }
	}

}
