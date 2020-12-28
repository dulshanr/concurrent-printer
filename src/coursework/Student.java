package coursework;

import java.util.ArrayList;

public class Student extends Thread {
    private PrinterMonitor printer;
    private ArrayList<Document> documentList;
    private String name;
    private int documentLabelHelper;



    public Student(PrinterMonitor printer,ThreadGroup threadGroup,String name,int DocumentLabelHelper) {
        super(threadGroup,name);
        this.printer = printer;
        this.name = name;
        this.documentLabelHelper = DocumentLabelHelper;

        documentList = new ArrayList<>();
    }

    public void addDocument(Document document) {
        this.documentList.add(document);
    }

    public void run()
    {
        generateRandomDocuments();
        for (int i = 0; i < this.documentList.size(); i++)
        {
            printer.addDocument(documentList.get(i));
            printer.printDocument(documentList.get(i));
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        printer.increaseFinishedCount();

    }

    public void generateRandomDocuments() {
        for (int i = 0; i < 5; i++) {
            documentList.add(new Document(this.getName(), "Document "+(i - (-documentLabelHelper)), generateRandomNumber(10, 19)));
        }
    }
    public int generateRandomNumber(int min,int max) {
        return ((int) (Math.random() * (max - min)) + 1)+min;
    }






}
