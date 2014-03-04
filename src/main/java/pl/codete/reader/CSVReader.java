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
 * @author Michal Burmer‚
 */
public class CSVReader {

//	public static void main(String[] args) throws ParseException, SQLException,
//        Exception {
//      int count = 3;
//      runApplication(count);
//	}

  public static void runApplication(int count) throws Exception {
    CSVReader csvReader = new CSVReader();
    for (int x = 1; x <= count; x++) {

        String filename = csvReader.getFilename(x);
        LineNumberReader reader = new LineNumberReader(new FileReader(filename));

        int offset = 0;
        int range = 250;

        while(null != reader.readLine()){
            int limit = offset + range;
            Map<Integer, Rows> rows = csvReader.readLines(reader, offset, limit);
            csvReader.insertToDb(new Database(), rows);
            offset = limit;
        }
        System.out.println("Ends file : " + x);
    }
  }
	
	private Map<Integer, Rows> readLines(LineNumberReader reader, int offset, int limit){
      Map<Integer, Rows> csvFileRows = new HashMap<Integer, Rows>();
      try {
          String line;
          reader.setLineNumber(offset);

          for (int progress = offset; progress < limit; progress++) {
            line = reader.readLine();
            if (line == null)
                break;

            String[] cells = line.split(";");
            long cost = (long) (Double.parseDouble(cells[0]) * 100);
            String calendarDate = cells[1];
            Rows row = new Rows(cost, calendarDate);
            csvFileRows.put(progress, row);
          }
      } catch (Exception e) {
          System.out.println(e.getMessage());
      }

      return csvFileRows;
	}

	private String getFilename(int x){
      StringBuilder fileName = new StringBuilder();
      return fileName
              .append("c:/task/CSVFileNumber")
              .append(x)
              .append(".csv")
              .toString();
	}
	
	private void insertToDb(Database database, Map<Integer, Rows> rows) throws Exception{
      Connection connection = database.GetConnection();
      Map<String, List<Rows>> allParameters = new HashMap<String, List<Rows>>();

      prepareDataToInserting(rows, allParameters, connection);
      insertPreparedDatasIntoDataBase(allParameters, connection);

      connection.close();
	}

	public static boolean isThereTableExist(Connection connection, String tabel)
        throws SQLException {
      DatabaseMetaData dataBaseMetas = connection.getMetaData();
      ResultSet tables = dataBaseMetas.getTables(null, null, tabel, null);
      return tables.next() ? true : false;
	}

	public static void createNewTable(Rows row, Connection connection)
          throws SQLException {
      String createTableSQL = "CREATE TABLE \"" + row.getDate()
              + "\" (id SERIAL PRIMARY KEY, cost BIGINT);";
      String createIndexSQL = "CREATE INDEX \"index" + row.getDate()+"\" ON \"" + row.getDate()+ "\" (cost)";
      
      Statement createNewTable = connection.createStatement();
      Statement createNewIndex = connection.createStatement();
      createNewTable.executeUpdate(createTableSQL);
      createNewIndex.executeUpdate(createIndexSQL);
      System.out.println("Created tabel named:" + row.getDate());
      System.out.println("Created index named:" + row.getDate());
      createNewTable.close();
      createNewIndex.close();
	}

	public static void createTableInDataBase(Connection connection, Rows row)
        throws SQLException {
      
      if (!isThereTableExist(connection, row.getDate())) {
          createNewTable(row, connection);
      }
	}

	private void prepareDataToInserting(
        Map<Integer, Rows> allRowsInCSVFile,
        Map<String, List<Rows>> allParameters, Connection connection)
        throws SQLException {

      int counter = 1;
      for (Entry<Integer, Rows> rowElement : allRowsInCSVFile.entrySet()) { 
          Rows row = rowElement.getValue();
          if (allParameters.containsKey(row.getDate())) {
              List<Rows> existedRowList = allParameters.get(row.getDate());
              existedRowList.add(row);
              allParameters.put(row.getDate(), existedRowList);
          } else {
              List<Rows> newRowList = new ArrayList<Rows>();
              newRowList.add(row);
              allParameters.put(row.getDate(), newRowList);
          }

          createTableInDataBase(connection, row);
          System.out.println("Added to map rekords: " + counter++ +" in cost: " + row.getCost());
      }
	}

	public static void insertPreparedDatasIntoDataBase(
        Map<String, List<Rows>> allParameters, Connection connection)
        throws SQLException {
      
      for (Entry<String, List<Rows>> rowElement : allParameters.entrySet()) {
        List<Rows> rowSortedByDate = rowElement.getValue();
        PreparedStatement pStatement = connection
                .prepareStatement("INSERT INTO \"" + rowElement.getKey()
                        + "\" (cost) VALUES (?);");
        connection.setAutoCommit(false);

        for (Rows row : rowSortedByDate) {
            pStatement.setLong(1, row.getCost());
            pStatement.addBatch();
        }

        pStatement.executeBatch();
        connection.commit();
        System.out.println("Execute Batch for: " + rowElement.getKey());
      }
	}
}
