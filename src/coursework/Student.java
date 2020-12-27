package coursework;

import java.util.ArrayList;

public class Student extends Thread {
    private PrinterMonitor printer;
    private ArrayList<Document> doc_list;
    private String name;

    public Student(PrinterMonitor printer,String name) {
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
            printer.addDocumentToBuffer(doc_list.get(i));
            printer.print(doc_list.get(i));
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
