package coursework;
/** ******************************************************************
 * File:      PrintingSystem.java (CLASS)
 * Author:    K.A.D.S Ratnayake
 * Contents:  6SENG002W CWK
 *            This defines the Printer the Printing system
 *            (This is the tester class).
 * Date:      28/12/20
 * Version:   1.0
 ****************************************************************** */

public class PrintingSystem {
    public static void main(String[] args) {
        //thread groups
        ThreadGroup studentThreadGroup = new ThreadGroup("StudentGroup");
        ThreadGroup technicianThreadGroup = new ThreadGroup("TechnicalGroup");
        //creating printer instance
        LaserPrinter printer = new LaserPrinter(201,100,4,"PrintMax V1.0");

        //creating student instances
        Student s1 = new Student(printer,studentThreadGroup,"Student_1",1);
        Student s2 = new Student(printer,studentThreadGroup,"Student_2",6);
        Student s3 = new Student(printer,studentThreadGroup,"Student_3",11);
        Student s4 = new Student(printer,studentThreadGroup,"Student_4",16);
        //creating toner and paper technicians.
        PaperTechnician t1 = new PaperTechnician(printer,technicianThreadGroup,"paper_technician",3);
        TonerTechnician t2 = new TonerTechnician(printer ,technicianThreadGroup,"toner_technician", 3);

        //starting the threads
        s1.start();
        s2.start();
        s3.start();
        s4.start();
        t1.start();
        t2.start();

        try {
            s1.join();
            s2.join();
            s3.join();
            s4.join();
            t1.join();
            t2.join();
            //thus main thread will only execute last print statement when the threads have finished
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All documents have been printed and the threads have finished their lifecycle");


    }
}
