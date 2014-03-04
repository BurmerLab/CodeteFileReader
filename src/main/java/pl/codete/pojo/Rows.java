package pl.codete.pojo;

import java.math.BigDecimal;
import java.util.Calendar;

/**
 *
 * @author Michał Burmer
 */
public class Rows {
  
  private long cost;
  private String date;

  public Rows(long cost, String date) {
    this.cost = cost;
    this.date = date;
  }

  public long getCost() {
    return cost;
  }

  public void setCost(long cost) {
    this.cost = cost;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

}
