package coursework;

public class Tester {
    public static void main(String[] args) {

        ThreadGroup studentThreadGroup = new ThreadGroup("StudentGroup");
        ThreadGroup technicianThreadGroup = new ThreadGroup("TechnicalGroup");

        PrinterMonitor printer = new PrinterMonitor(4,"PrintMax V1.0");

        Student s1 = new Student(printer,studentThreadGroup,"student1",1);

        Student s2 = new Student(printer,studentThreadGroup,"student2",6);

        Student s3 = new Student(printer,studentThreadGroup,"student3",11);

        Student s4 = new Student(printer,studentThreadGroup,"student4",16);

        PaperTechnician t1 = new PaperTechnician(printer,technicianThreadGroup,"paper_technician",3);
        TonerTechnician t2 = new TonerTechnician(printer ,technicianThreadGroup,"toner_technician", 3);

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
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All documents have been printed and the threads have finished their lifecycle");


    }
}
