package pl.codete.reader;

import java.io.FileReader;
import java.io.LineNumberReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;
import javax.sound.midi.Soundbank;
import pl.codete.database.Database;
import pl.codete.database.dao.QUERY_PARAMS;
import pl.codete.pojo.Rows;

/**
 *
 * @author Michał
 */
public class CSVReader {
     public static Map<Integer, Rows> allRowsInCSVFile = new HashMap<Integer, Rows>();
    
      public static void main(String [] args) throws ParseException, SQLException, Exception{
        String file = "c:/task/csvFileNumer1.csv";
        CSVReader.readFile(file);
        Database database = new Database();
        Connection connection = database.GetConnection();
        int batchSize = 1000;
        long startTime = System.currentTimeMillis();
        
        PreparedStatement ps = connection.prepareStatement(QUERY_PARAMS.INSERT_ROW_INTO_DATABASE);
//        connection.setAutoCommit(false);
        
        for(int x=0; x<=allRowsInCSVFile.size(); x++){
          
          Rows row = allRowsInCSVFile.get(x);
          System.out.println("kwota:" + row.getCost());
          ps.setLong(1, row.getCost());
          ps.setString(2, row.getDate());
          ps.addBatch();

          if (x % batchSize == 0) {
              ps.executeBatch();
            }
          }
          ps.executeBatch();
//          connection.commit();
          connection.close();
          long endTime = System.currentTimeMillis();
          long elapsedTime = (endTime - startTime)/1000;
          System.out.println("time: " + elapsedTime);
//          submit the batch for execution
//          int[] insertCounts = ps.executeBatch();
//          ps .executeUpdate();
      }
      
      
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
}
