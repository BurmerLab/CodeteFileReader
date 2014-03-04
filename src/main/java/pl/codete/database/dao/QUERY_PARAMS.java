package pl.codete.database.dao;

/**
 *
 * @author Michał
 */
public class QUERY_PARAMS {
  
  
  
  public final static String INSERT_ROW_INTO_DATABASE = "INSERT INTO t_rows(cost, date) VALUES (?,?)";
  public final static String AVG_COST_FROM_TABLE = "SELECT AVG(cost) FROM ";
  public final static String MIN_COST_FROM_TABLE = "SELECT MIN(cost) FROM ";
  public final static String MAX_COST_FROM_TABLE = "SELECT MAX(cost) FROM ";
  public final static String FIND_NAMES_ALL_TABLES_IN_DB = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA= 'public'";
  
}
