package coursework;

import java.util.ArrayList;

public class Student extends Thread {
    private PrinterMonitor printer;
    private ArrayList<Document> doc_list;
    private String name;



    public Student(PrinterMonitor printer,ThreadGroup threadGroup,String name) {
        super(threadGroup,name);
        this.printer = printer;
        this.name = name;
        doc_list = new ArrayList<>();
    }

    public void addDocument(Document document) {
        this.doc_list.add(document);
    }

    public void run()
    {
        for (int i = 0; i < this.doc_list.size(); i++)
        {
            printer.addDocument(doc_list.get(i));
            printer.printDocument(doc_list.get(i));
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printer.increaseFinishedCount();

    }



}
