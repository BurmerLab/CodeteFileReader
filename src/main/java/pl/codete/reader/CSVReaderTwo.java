package pl.codete.reader;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import pl.codete.database.Database;
import pl.codete.pojo.Rows;

/**
 *
 * @author Michał
 */
public class CSVReaderTwo {
    
    public static void main(String [] args) throws ParseException, SQLException, Exception{
       
      for(int x=1; x<=1; x++){
          parseAndAddToDataBaseFile(x);
          System.out.println("Ends file : " + x);
        }
      }
      
      public static void parseAndAddToDataBaseFile(int numbers) throws ParseException, SQLException, Exception{
        Map<Integer, Rows> allRowsInCSVFile = new HashMap<Integer, Rows>();
        Map<String, List<Rows>> allParameters = new HashMap<String, List<Rows>>();
        StringBuilder fileName = new StringBuilder();
        fileName.append("c:/task/CSVFileNumber").append(numbers).append(".csv");
        
        Database database = new Database();
        Connection connection = database.GetConnection();
        
        allRowsInCSVFile = CSVReaderTwo.readFile(fileName.toString());
        prepareDataToInserting(allRowsInCSVFile, allParameters, connection);
        insertPreparedDatasIntoDataBase(allParameters, connection);
        connection.close();
      }
      
        public static Map<Integer, Rows> readFile(String fileName){
            Map<Integer, Rows> allRowsFromCSVFile = new HashMap<Integer, Rows>();
            try{
                LineNumberReader reader = new LineNumberReader(new FileReader(fileName));
                String line;
                
                for (int progress = 0; ; progress++){
                  line = reader.readLine();
                  if (line == null) break;
                  
                  String[] cells = line.split(";");
                  long cost = (long) (Double.parseDouble(cells[0]) * 100);
                  String calendarDate = cells[1];
                  Rows row = new Rows(cost,calendarDate);
//                  row.setCost(cost);
//                  row.setDate(calendarDate); 
                  allRowsFromCSVFile.put(progress, row);
                }
            }
            catch (Exception e){
               System.out.println(e.getMessage());
            }
          return allRowsFromCSVFile;
        }

  public static boolean isThereTableExist(Connection connection, String tabel) throws SQLException {
    DatabaseMetaData dataBaseMetas = connection.getMetaData();
    ResultSet tables = dataBaseMetas.getTables(null, null, tabel, null);
    return tables.next() ? true : false;
  }

  public static void createNewTable(Rows row, Connection connection) throws SQLException {
    String createTableSQL = "CREATE TABLE \"" + row.getDate() + "\" (id SERIAL PRIMARY KEY, cost BIGINT);";
    
    Statement createNewTable = connection.createStatement();
    createNewTable.executeUpdate(createTableSQL);
    System.out.println("Utworzono tabele o nazwie:" + row.getDate());
    createNewTable.close();
  }

  public static void createTableInDataBase(Connection connection, Rows row) throws SQLException {
    if(!isThereTableExist(connection, row.getDate())){
      createNewTable(row, connection);
      }
  }

  public static void prepareDataToInserting(Map<Integer, Rows> allRowsInCSVFile, Map<String, List<Rows>> allParameters, Connection connection) throws SQLException {
    for(int x=0; x<=allRowsInCSVFile.size()-1; x++){
      Rows row = allRowsInCSVFile.get(x);
      
      if(allParameters.containsKey(row.getDate())){
        List<Rows> existedRowList = allParameters.get(row.getDate());
        existedRowList.add(row);
        allParameters.put(row.getDate(), existedRowList);
      }else{
        List<Rows> newRowList = new ArrayList<Rows>();
        newRowList.add(row);
        allParameters.put(row.getDate(), newRowList);
      }
      
      createTableInDataBase(connection, row);
      System.out.println("Added to map rekords: " + x);
    }
  }

  public static void insertPreparedDatasIntoDataBase(Map<String, List<Rows>> allParameters, Connection connection) throws SQLException {
    for(Entry<String, List<Rows>> rowElement : allParameters.entrySet()) {
      List<Rows> rowSortedByDate = rowElement.getValue();
      PreparedStatement pStatement = connection.prepareStatement("INSERT INTO \""+ rowElement.getKey() +"\" (cost) VALUES (?);");
      connection.setAutoCommit(false);
      
      for(Rows row : rowSortedByDate){
        pStatement.setLong(1, row.getCost());
        pStatement.addBatch();
      }
      
      pStatement.executeBatch();
      connection.commit();
      System.out.println("Execute Batch for: " + rowElement.getKey());
    }
  }
}
