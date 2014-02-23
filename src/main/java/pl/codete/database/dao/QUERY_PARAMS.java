/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.codete.database.dao;

/**
 *
 * @author Michał
 */
public class QUERY_PARAMS {
  
  //main
  public final static String AND = " AND ";
  public final static String WHERE = " WHERE ";
  public final static String OR = " OR ";
  public final static String LIMIT = " LIMIT ";
  
  
  public final static String INSERT_ROW_INTO_DATABASE = "INSERT INTO t_rows(cost, date) VALUES (?,?)";
  
  //points
  public final static String SELECT_ALL_RECORDS_FROM_POINT = "SELECT osmid, latitude, longitude, degree FROM points";
  public final static String SELECT_RECORD_FROM_POINT_BY_OSM_ID = "SELECT osmid, latitude, longitude, degree FROM points WHERE osmid=";

  //segments
  public final static String SELECT_ALL_RECORDS_FROM_SEGMENTS = "SELECT ids, osm_way_id, osm_start_node_id, osm_end_node_id,  length_km, max_speed_km_h, directed, start_azimuth, end_azimuth FROM segments";
  public final static String SELECT_ALL_RECORDS_FROM_SEGMENTS_WITH_LIMIT = "SELECT ids, osm_way_id, osm_start_node_id, osm_end_node_id,  length_km, max_speed_km_h, directed, start_azimuth, end_azimuth FROM segments";
  public final static String SELECT_RECORD_FROM_SEGMENTS_BY_IDS = "SELECT ids, osm_way_id, osm_start_node_id, osm_end_node_id, length_km, max_speed_km_h, directed, start_azimuth, end_azimuth, passing_time FROM segments WHERE ids=";
  public final static String UPDATE_ALL_GENERATED_RECORDS_IN_SEGMENTS_TABLE = "UPDATE segmentstest SET passing_time=?, traffic=? WHERE ids=";
  public final static String COUNT_ALL_RECORDS_FROM_SEGMENTS = "SELECT count(*) FROM segments ";

}
