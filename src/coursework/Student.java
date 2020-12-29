package coursework;
/** ******************************************************************
 * File:      Student.java (CLASS)
 * Author:    K.A.D.S Ratnayake
 * Contents:  6SENG002W CWK
 *            This defines the Student class .
 * Date:      28/12/20
 * Version:   1.0
 ****************************************************************** */

import java.util.ArrayList;

public class Student extends Thread {
    //student attributes
    private LaserPrinter printer;
    private ArrayList<Document> documentList;
    private int documentLabelHelper;
    /*
        helper attribute to label the documents
        since each student has different documents.
     */


    public Student(LaserPrinter printer, ThreadGroup threadGroup, String name, int DocumentLabelHelper) {
        super(threadGroup,name);
        this.printer = printer;
        this.documentLabelHelper = DocumentLabelHelper;
        documentList = new ArrayList<>();

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
        // update the finished counter
        printer.increaseFinishedCount();
    }
    //generate documents with random page numbers
    public void generateRandomDocuments() {
        for (int i = 0; i < 5; i++) {
            documentList.add(new Document(this.getName(), "Document "+(i - (-documentLabelHelper)), generateRandomNumber(10, 19)));
        }
    }
    //logic to generate random numbers
    public int generateRandomNumber(int min,int max) {
        return ((int) (Math.random() * (max - min)) + 1)+min;
    }


}
