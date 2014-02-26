/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.codete.test;

/**
 *
 * @author michal.burmer
 */
public class MyThread extends Thread{

     //Do what I need here on a thread
     public void run(){
         //Do what I need here
       System.out.println("run()" + Thread.currentThread().getId());
       //program do zapisywania do DB
     }
}
