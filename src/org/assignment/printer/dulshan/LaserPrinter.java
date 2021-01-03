package org.assignment.printer.dulshan;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
/** ******************************************************************
 * File:      LaserPrinter.java (CLASS)
 * Author:    K.A.D.S Ratnayake
 * Contents:  6SENG002W CWK
 *            This defines the Printer monitor.
 * Date:      28/12/20
 * Version:   1.0
 ****************************************************************** */

public class LaserPrinter implements ServicePrinter {
    //present paper level.
    private int currentPaperLevel ;
    //present toner level.
    private int currentTonerLevel;
    private ArrayList<Document> documentBuffer;
    //count finished students so that other threads can successfully end.
    private int finishedStudentThreads;
    //total student threads.
    private int totalStudentThreads;
    private String printerId;
    //count of printed documents.
    private int printedDocumentCount;
    /*
    Document buffer was added to prevent deadlock when
    pages of document to be printed >currentTonerLevel and
    currentTonerLevel>10.
     */

    //constructor.
    public LaserPrinter(int initialPaperLevel,int initialTonerLevel,int totalStudentThreads, String printerId) {

        this.totalStudentThreads = totalStudentThreads;
        this.documentBuffer = new ArrayList<>();
        this.printerId = printerId;
        currentTonerLevel = initialTonerLevel;
        currentPaperLevel = initialPaperLevel;
        finishedStudentThreads = 0;
        printedDocumentCount = 0;

    }
    //printing method process.
    public synchronized void printDocument(Document document) {
        System.out.println(logHelper(document.getDocOwner()+Utility.EMPTY_SPACE+"attempting to print"
                +Utility.EMPTY_SPACE +document.getDocTitle()+Utility.EMPTY_SPACE+"of"
                +Utility.EMPTY_SPACE+document.getPages()+Utility.EMPTY_SPACE+"pages"));
        /*
        checks whether available toner is not enough for document to be printed or available paper level is
        not enough to print the document or the document buffer is empty.
         */
        while ( (document.getPages()> currentTonerLevel) || (document.getPages()> currentPaperLevel) || documentBuffer.size() == 0)
        {
            try {
                wait() ;
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        //printing process.
        currentPaperLevel -= document.getPages();
        currentTonerLevel -= document.getPages();
        //removing document from buffer.
        documentBuffer.remove(document);
        printedDocumentCount ++;

        System.out.println(logHelper(document.getDocOwner()+Utility.COMMA+Utility.EMPTY_SPACE
                +"AFTER PRINTING STATE"+Utility.EMPTY_SPACE +this.toString()));

        notifyAll();
    }
//adding document to buffer
    public synchronized void addDocument(Document document) {
        while (documentBuffer.size() != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        documentBuffer.add(document);
        notifyAll();

    }
//refill paper whenever possible
    public synchronized void refillPaper() {
        /*
        try to refill as soon as space is available
        i.e below condition.
         */
        while ( !(Full_Paper_Tray - currentPaperLevel >=SheetsPerPack) )
        {
            try {
                if (finishedStudentThreads == totalStudentThreads) {
                    System.out.println(Utility.GREEN_BOLD+"Students have finished printing all the documents, " +
                            "therefore Paper Technician is exiting the attempt"+Utility.RESET);
                    return;
                }
                wait() ;
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        //refilling process.
        currentPaperLevel += SheetsPerPack;
        System.out.println(logHelper(Utility.GREEN_BOLD+"AFTER REFILLING PAPER"+Utility.EMPTY_SPACE
                +this.toString())+Utility.RESET);
        notifyAll();

    }
    //replace toner method
    public synchronized void replaceTonerCartridge() {
        /*
            make sure current toner is enough to print the document in the buffer and
            toner level is anyway greater than 10.
         */
        while ( (getMinimumPagesFromBuffer()< currentTonerLevel && currentTonerLevel >Minimum_Toner_Level))
        {
            try {
                /*
                    condition to get back to toner class and finish iteration to end the thread
                    as all students have finished printing.
                 */
                if (finishedStudentThreads == totalStudentThreads) {
                    System.out.println(Utility.YELLOW_BOLD+"Students have finished printing all the documents, " +
                            "therefore Toner Technician is exiting the attempt"+Utility.RESET);
                    return;
                }
                wait() ;
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        //replacing toner.
        currentTonerLevel = PagesPerTonerCartridge;

        System.out.println(logHelper(Utility.YELLOW_BOLD+"AFTER REPLACING TONER"
                +Utility.EMPTY_SPACE+this.toString()+Utility.RESET));
        notifyAll();

    }
    //increase the count when a student finishes printing all the documents.
    public synchronized void increaseFinishedCount() {
        finishedStudentThreads++;
        notifyAll();
    }

    /*
        logic implemented to get the minimum pages from
        document in the buffer.
     */
    public int getMinimumPagesFromBuffer() {
        int min = -1;
        for (int i = 0; i< documentBuffer.size(); i++){
            if (i == 0) {
                min = documentBuffer.get(0).getPages();
            } else if (documentBuffer.get(i).getPages() < min) {
                min = documentBuffer.get(i).getPages();
            }
        }
        return min;
    }

    //toString method to print the current status of the printer.
    @Override
    public String toString() {
        return "PrinterStatus: [" +
                "printerID=" + printerId +
                ", currentPaperLevel=" + currentPaperLevel +
                ", currentTonerLevel=" + currentTonerLevel +
                ", printedDocumentCount=" + printedDocumentCount +
                ']';
    }
    //helper function to log the message with the date.
    public String logHelper(String message) {
        String dateTimeString = Utility.LEFT_BRACKET+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
                .format(new Date())+Utility.RIGHT_BRACKET+Utility.EMPTY_SPACE;
        return dateTimeString+message;
    }

}
