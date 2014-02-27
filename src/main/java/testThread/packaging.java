/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testThread;

/**
 *
 * @author Michał
 */
public class packaging {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    int all = 211;
    int divide = 4;
    int pack = all / divide;
    int rest = all % divide;
    System.out.println("rest: " + rest);
    int start = 1;
    
    for(int x=1; x<=4; x++){
      int stop = pack * x;
      if(x==4){
        int lastStop = stop + rest;
        System.out.println("start: " + start + " stop: " + lastStop);
      }else{
        System.out.println("start: " + start + " stop: " + stop);
      }
      start = stop + 1;
    }
  }
}
