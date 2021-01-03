package org.assignment.printer.dulshan;
/** ******************************************************************
 * File:      PaperTechnician.java (CLASS)
 * Author:    K.A.D.S Ratnayake
 * Contents:  6SENG002W CWK
 *            This defines the Paper Technician's abilities.
 * Date:      28/12/20
 * Version:   1.0
 ****************************************************************** */

public class PaperTechnician extends Thread {
//paper technician attributes
    private LaserPrinter printer;
    private int attempts;

//constructor
    public PaperTechnician(LaserPrinter printer, ThreadGroup threadGroup, String name, int attempts) {
        super(threadGroup,name);
        this.printer = printer;
        this.attempts = attempts;
    }

//starting the thread will invoke this method
    public void run()
    {
        int i = 0;
        //follow the loop until all students have finished printing and attempts is less than 3
        while (i<attempts) {
            System.out.println(Utility.GREEN_BOLD+"Paper technician attempting " +
                    "to refill papers with attempt : "+((i)-(-1))+Utility.RESET);
            printer.refillPaper();
            try {
                sleep(generateRandomNumber(1,5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }

    }
    //logic to generate a random from a range
    public int generateRandomNumber(int min,int max) {
        return ((int) (Math.random() * (max - min)) + 1)+min;
    }
}
