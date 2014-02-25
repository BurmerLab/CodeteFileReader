/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.codete.test;

/**
 *
 * @author michal.burmer
 */
public class MyProgramm{

//Program main
public static void main(String[] args) {

                //Send 10 threads
        for (int i=0; i<10; i++){

                  //Init class (threaded)
                   MyThread threadClass = new MyThread();

                   //Execute code in the class run() method
                   threadClass.start();
            }
    }
}
