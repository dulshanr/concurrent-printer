package coursework;

public class Tester {
    public static void main(String[] args) {

        ThreadGroup studentThreadGroup = new ThreadGroup("StudentGroup");
        ThreadGroup technicianThreadGroup = new ThreadGroup("TechnicalGroup");

        PrinterMonitor printer = new PrinterMonitor(4,"PrintMax V1.0");


        Document d1 = new Document("owner1", "title1", 10);
        Document d2 = new Document("owner1", "title2", 20);
        Document d3 = new Document("owner1", "title3", 20);
        Document d4 = new Document("owner1", "title4", 10);
        Document d5 = new Document("owner1", "title5", 15);

        Student s1 = new Student(printer,studentThreadGroup,"student1");
        s1.addDocument(d1);
        s1.addDocument(d2);
        s1.addDocument(d3);
        s1.addDocument(d4);
        s1.addDocument(d5);

        Document d6 = new Document("owner2", "title6", 5);
        Document d7 = new Document("owner2", "title7", 20);
        Document d8 = new Document("owner2", "title8", 20);
        Document d9 = new Document("owner2", "title9", 10);
        Document d10 = new Document("owner2", "title10", 15);

        Student s2 = new Student(printer,studentThreadGroup,"student2");
        s2.addDocument(d6);
        s2.addDocument(d7);
        s2.addDocument(d8);
        s2.addDocument(d9);
        s2.addDocument(d10);

        Student s3 = new Student(printer,studentThreadGroup,"student3");
        Document d11 = new Document("owner3", "title11", 5);
        Document d12 = new Document("owner3", "title12", 7);
        Document d13 = new Document("owner3", "title13", 10);
        Document d14 = new Document("owner3", "title14", 45);
        Document d15 = new Document("owner3", "title15", 12);

        s3.addDocument(d11);
        s3.addDocument(d12);
        s3.addDocument(d13);
        s3.addDocument(d14);
        s3.addDocument(d15);


        Student s4 = new Student(printer,studentThreadGroup,"student4");
        Document d16 = new Document("owner4", "title16", 5);
        Document d17 = new Document("owner4", "title17", 10);
        Document d18 = new Document("owner4", "title18", 15);
        Document d19 = new Document("owner4", "title19", 5);
        Document d20 = new Document("owner4", "title20", 35);

        s4.addDocument(d16);
        s4.addDocument(d17);
        s4.addDocument(d18);
        s4.addDocument(d19);
        s4.addDocument(d20);


        PaperTechnician t1 = new PaperTechnician(printer,technicianThreadGroup,"paper_technician",3);
        TonerTechnician t2 = new TonerTechnician(printer ,technicianThreadGroup,"toner_technician", 3);

        s1.start();
        s2.start();
        s3.start();
        s4.start();
        t1.start();
        t2.start();







    }
}
