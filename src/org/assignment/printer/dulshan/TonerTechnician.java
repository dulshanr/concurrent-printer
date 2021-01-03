package org.assignment.printer.dulshan;
/** ******************************************************************
 * File:      TonerTechnician.java (CLASS)
 * Author:    K.A.D.S Ratnayake
 * Contents:  6SENG002W CWK
 *            This defines the Toner Technician Class.
 * Date:      28/12/20
 * Version:   1.0
 ****************************************************************** */

public class TonerTechnician extends Thread {
    //paper technician attributes
    private final LaserPrinter printer;
    private int attempts;

//constructor.
    public TonerTechnician(LaserPrinter printer, ThreadGroup threadGroup, String name, int attempts) {
        super(threadGroup,name);
        this.printer = printer;
        this.attempts = attempts;
    }

    //starting the thread will invoke this method.
    public void run()
    {
        int i = 0;
        //follow the loop until all students have finished printing and attempts is less than 3
        while (i<attempts) {
            System.out.println(Utility.YELLOW_BOLD+"Toner technician attempting " +
                    "to replace the toner with attempt : "+((i)-(-1))+Utility.RESET);
            printer.replaceTonerCartridge();
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
