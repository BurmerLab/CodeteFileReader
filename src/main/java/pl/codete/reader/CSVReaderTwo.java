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
     public static Map<Integer, Rows> allRowsInCSVFile = new HashMap<Integer, Rows>();
     public static Map<String, List<Rows>> allParameters = new HashMap<String, List<Rows>>();
    
      public static void main(String [] args) throws ParseException, SQLException, Exception{
        String file = "c:/task/csvFileNumer2.csv";
        int batchSize = 4;
        CSVReaderTwo.readFile(file);
        
        Database database = new Database();
        Connection connection = database.GetConnection();
        long startTime = System.currentTimeMillis();
        
        PreparedStatement pStatement = null;
        
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
//          if(!isThereTableExist(connection, row.getDate())){
//            createNewTable(row, connection);
//            }
          System.out.println("Dodano do MAPY rekordow: " + x);
          }
        for(Entry<String, List<Rows>> rowElement : allParameters.entrySet()) {
          String key = rowElement.getKey();
          List<Rows> rowSortedByDate = rowElement.getValue();
          
          for(Rows row : rowSortedByDate){
            System.out.println("dla Kluczu: " + key);
            System.out.println("sa wartosci: " + row.getCost());
          }
        }
        // okej pakuje se w odpowienie mapy
        // teraz zrobic:
        
        // kazda mape z danym kluczem, zapakowac do BD tzn.
        // dlaklucza 1/1/2013 zapakowac cala liste do addBatch, dopiero pozniej batch execute :)
        
        
//          pStatement = connection.prepareStatement("INSERT INTO \""+ row.getDate() +"\" (cost) VALUES (?);");
//          pStatement.setLong(1, row.getCost());
//          pStatement.addBatch();
          
          
          pStatement.executeBatch();
          connection.close();
          
          long endTime = System.currentTimeMillis();
          long elapsedTime = (endTime - startTime) / 1000;
          System.out.println("all time: " + elapsedTime +"sek");
      }
      
      
//          if (x % batchSize == 0) {
//              pStatement.executeBatch();
//              System.out.println("Wybilo 1000");
//            }
      
        public static void readFile(String fileName){
            try{
                LineNumberReader reader = new LineNumberReader(new FileReader(fileName));
                String line;
                
                for (int progress = 0; ; progress++){
                    
                  line = reader.readLine();
                  if (line == null) break;
                  
                  String[] cells = line.split(";");
                  Rows row = new Rows();
                  
                  long cost = (long) (Double.parseDouble(cells[0]) * 100);
                  row.setCost(cost);
                  
//                  System.out.println("cost dodany: " + cost);
                  
                  String calendarDate = cells[1];
                  row.setDate(calendarDate); 
//                  System.out.println("date dodany: " + calendarDate);
                  
                  allRowsInCSVFile.put(progress, row);
                  //lub klucz to data
//                  allRowsInCSVFile.put(row.getDate(), row);

                }
            }
            catch (Exception e){
               System.out.println(e.getMessage());
            }
        }

  public static boolean isThereTableExist(Connection connection, String tabel) throws SQLException {
    DatabaseMetaData dataBaseMetas = connection.getMetaData();
    ResultSet tables = dataBaseMetas.getTables(null, null, tabel, null);
    if (tables.next()) {
      System.out.println("CONTINUE- Tabela "+ tabel+" istnieje ");
      return true;
    }else{
      System.out.println("Tabela "+ tabel+" nie istnieje");
      return false;
    }
  }

  public static void createNewTable(Rows row, Connection connection) throws SQLException {
    String createTableSQL = "CREATE TABLE \"" + row.getDate() + "\" (id SERIAL PRIMARY KEY, cost BIGINT);";
    
    Statement createNewTable = connection.createStatement();
    createNewTable.executeUpdate(createTableSQL);
    System.out.println("Utworzono tabele o nazwie:" + row.getDate());
    createNewTable.close();
    
  }
}
