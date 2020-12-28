package coursework;

import java.util.ArrayList;

public class PrinterMonitor implements ServicePrinter {

    private int currentPaperLevel ;
    private int currentTonerLevel;
    private ArrayList<Document> documentBuffer;
    private int finishedStudentThreads;
    private int totalStudentThreads;
    private String printerId;
    private int printedCount;


    public PrinterMonitor( int totalStudentThreads,String printerId) {

        this.totalStudentThreads = totalStudentThreads;
        this.documentBuffer = new ArrayList<>();
        this.printerId = printerId;
        currentTonerLevel = Full_Toner_Level;
        currentPaperLevel = Full_Paper_Tray;
        finishedStudentThreads = 0;
        printedCount = 0;

    }

    public synchronized void printDocument(Document document) {

        while ( (document.getPages()> currentTonerLevel) || (document.getPages()> currentPaperLevel) || documentBuffer.size() == 0)
        {
            try {
                wait() ;
            } catch(InterruptedException e){ }
        }


        currentPaperLevel -= document.getPages();
        currentTonerLevel -= document.getPages();
        documentBuffer.remove(document);
        printedCount++;

        System.out.println("Printing, Owner: " + document.getDocOwner() + " Title: "
                + document.getDocTitle() + " NoPages: " + document.getPages()+" Printer Level: pages_avail "+ currentPaperLevel +" toner: avail "+ currentTonerLevel);

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

        while ( !(Full_Paper_Tray - currentPaperLevel >=SheetsPerPack) )
        {
            try {
                if (finishedStudentThreads == totalStudentThreads) {
                    return;
                }
                wait() ;
            } catch(InterruptedException e){ }
        }
        currentPaperLevel += SheetsPerPack;
        System.out.println("RefillPaper, paper with refilled: " + currentPaperLevel);
        notifyAll();

    }

    public synchronized void replaceTonerCartridge() {
        while ( (getMinimumPagesFromBuffer()< currentTonerLevel && currentTonerLevel >Minimum_Toner_Level))
        {
            try {
                if (finishedStudentThreads == totalStudentThreads) {
                    return;
                }
                wait() ;
            } catch(InterruptedException e){ }
        }
        currentTonerLevel = PagesPerTonerCartridge;

        System.out.println("RefillToner, toner with refilled: " + currentTonerLevel);
        notifyAll();

    }

    public synchronized void increaseFinishedCount() {
        finishedStudentThreads++;
        notifyAll();
    }
    public synchronized int getFinishedcount() {
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

    public void logHelper() {

    }


    //    public void displayList() {
//        System.out.println("Total No: "+document_buffer.size());
//    }


}
