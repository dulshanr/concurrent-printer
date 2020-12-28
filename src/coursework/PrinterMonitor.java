package coursework;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PrinterMonitor implements ServicePrinter {
    private int currentPaperLevel ;
    private int currentTonerLevel;
    private ArrayList<Document> documentBuffer;
    private int finishedStudentThreads;
    private int totalStudentThreads;
    private String printerId;
    private int printedDocumentCount;


    public PrinterMonitor( int totalStudentThreads,String printerId) {

        this.totalStudentThreads = totalStudentThreads;
        this.documentBuffer = new ArrayList<>();
        this.printerId = printerId;
        currentTonerLevel = Full_Toner_Level;
        currentPaperLevel = Full_Paper_Tray;
        finishedStudentThreads = 0;
        printedDocumentCount = 0;

    }

    public synchronized void printDocument(Document document) {
        System.out.println(logHelper(document.getDocOwner()+" attempting to print "
                +document.getDocTitle()+" of "+document.getPages()+ " pages"));
        while ( (document.getPages()> currentTonerLevel) || (document.getPages()> currentPaperLevel) || documentBuffer.size() == 0)
        {
            try {
                wait() ;
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }


        currentPaperLevel -= document.getPages();
        currentTonerLevel -= document.getPages();
        documentBuffer.remove(document);
        printedDocumentCount ++;

        System.out.println(logHelper("AFTER PRINTING STATE "+this.toString()));


//        String message = "Printing, Owner: " + document.getDocOwner() + " Title: "
//                + document.getDocTitle() + " NoPages: " + document.getPages()+" Printer Level: pages_avail "+ currentPaperLevel +" toner: avail "+ currentTonerLevel;

        notifyAll();
    }

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

    public synchronized void refillPaper() {
        System.out.println(logHelper("Paper Technician attempting to refill paper"));

        while ( !(Full_Paper_Tray - currentPaperLevel >=SheetsPerPack) )
        {
            try {
                if (finishedStudentThreads == totalStudentThreads) {
                    return;
                }
                wait() ;
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        currentPaperLevel += SheetsPerPack;
        System.out.println(logHelper("AFTER REFILLING PAPER "+this.toString()));
        notifyAll();

    }

    public synchronized void replaceTonerCartridge() {
        System.out.println(logHelper("Toner Technician attempting to replace toner"));
        while ( (getMinimumPagesFromBuffer()< currentTonerLevel && currentTonerLevel >Minimum_Toner_Level))
        {
            try {
                if (finishedStudentThreads == totalStudentThreads) {
                    return;
                }
                wait() ;
            } catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        currentTonerLevel = PagesPerTonerCartridge;

        System.out.println(logHelper("AFTER REPLACING TONER "+this.toString()));
        notifyAll();

    }

    public synchronized void increaseFinishedCount() {
        finishedStudentThreads++;
        notifyAll();
    }
    public synchronized int getFinishedCount() {
        return finishedStudentThreads;
    }


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

    public int getTotalStudentThreads() {
        return totalStudentThreads;
    }

    @Override
    public String toString() {
        return "PrinterStatus: [" +
                "printerID=" + printerId +
                ", currentPaperLevel=" + currentPaperLevel +
                ", currentTonerLevel=" + currentTonerLevel +
                ", printedCount=" + printedDocumentCount +
                ']';
    }

    public String logHelper(String message) {
        String dateTimeString = "("+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date())+") ";
        return dateTimeString+message;
    }

}
