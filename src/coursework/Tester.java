package coursework;

public class Tester {
    public static void main(String[] args) {

        PrinterMonitor printer = new PrinterMonitor();

        Document d1 = new Document("owner1", "title1", 10);
        Document d2 = new Document("owner1", "title2", 20);
        Document d3 = new Document("owner1", "title3", 20);
        Document d4 = new Document("owner1", "title4", 10);
        Document d5 = new Document("owner1", "title5", 15);

        Student s1 = new Student(printer,"student1");
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

        Student s2 = new Student(printer,"student2");
        s2.addDocument(d6);
        s2.addDocument(d7);
        s2.addDocument(d8);
        s2.addDocument(d9);
        s2.addDocument(d10);

        PaperTechnician t1 = new PaperTechnician(printer,20);
        TonerTechnician t2 = new TonerTechnician(printer, 20, 3);

        s1.start();
        s2.start();
        t1.start();
        t2.start();




    }
}
