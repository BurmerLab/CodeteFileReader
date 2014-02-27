package pl.codete.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import pl.codete.database.Database;
import pl.codete.pojo.Raport;
import pl.codete.pojo.Rows;

/**
 *
 * @author Michał
 */
public class RowsDAO {
  
   public static void main(String [] args) throws ParseException, SQLException, Exception{
       Database database = new Database();
       Connection connection = database.GetConnection();
       
       List<Raport> list = obtainAllRaportObjects(connection, findNamesAllTablesInDB(connection));
       List<String> tablesNames = findNamesAllTablesInDB(connection);
       for(Raport raport : list){
         System.out.println("AVG: " + raport.getAvgValue());
         System.out.println("MIN " + raport.getMinValue());
         System.out.println("MAX " + raport.getMaxValue());
        }
    }
  
    public static ArrayList<Raport> obtainAllRaportObjects(Connection connection, ArrayList<String> tablesNames) throws Exception {
      double divide = 100.0;
      ArrayList<Raport> allRaportObjects = new ArrayList<Raport>();
      for(String tableName : tablesNames){
        try {
          PreparedStatement psAvg = connection.prepareStatement(QUERY_PARAMS.AVG_COST_FROM_TABLE +"\""+ tableName + "\"");
          PreparedStatement psMin = connection.prepareStatement(QUERY_PARAMS.MIN_COST_FROM_TABLE +"\""+ tableName + "\"");
          PreparedStatement psMax = connection.prepareStatement(QUERY_PARAMS.MAX_COST_FROM_TABLE +"\""+ tableName + "\"");
          ResultSet rsAvg = psAvg.executeQuery();
          ResultSet rsMin = psMin.executeQuery();
          ResultSet rsMax = psMax.executeQuery();

          Raport feedRaport = new Raport();
          while(rsAvg.next() && rsMin.next() && rsMax.next()) {
            feedRaport.setAvgValue(rsAvg.getDouble(1) / divide);
            feedRaport.setMinValue(rsMin.getDouble(1) / divide);
            feedRaport.setMaxValue(rsMax.getDouble(1) / divide);
          }
          allRaportObjects.add(feedRaport);
        }
        catch(Exception e){
                throw e;
        }
      }
      return allRaportObjects;
    }
  
  public static ArrayList<String> findNamesAllTablesInDB(Connection connection) throws Exception {

        ArrayList<String> allTablesNames = new ArrayList<String>();
        PreparedStatement ps = connection.prepareStatement(QUERY_PARAMS.FIND_NAMES_ALL_TABLES_IN_DB);
        ResultSet rs = ps.executeQuery();
        
        while(rs.next() ) {
          allTablesNames.add(rs.getString(1));
        }
      return allTablesNames;
    }
  
  
  
}
