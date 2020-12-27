package coursework;

import java.util.ArrayList;

public class PrinterMonitor {

    private int papers = 100;
    private int maxPapers = 100;
    private int currentToner = 90;
    private ArrayList<Document> document_buffer = new ArrayList<>();



    public synchronized void print(Document document) {

        while ( (document.getPages()>currentToner) || (document.getPages()>papers))
        {
            try {
                wait() ;
            } catch(InterruptedException e){ }
        }
        papers -= document.getPages();
        currentToner -= document.getPages();
        document_buffer.remove(document);

        System.out.println("Printing, Owner: " + document.getDocOwner() + " Title: "
                + document.getDocTitle() + " NoPages: " + document.getPages()+" Printer Level: pages_avail "+papers+" toner: avail "+currentToner);

        notifyAll();
    }
    public synchronized void refillPaper(int paperstack) {

        while ( !(maxPapers-papers>=paperstack) )
        {
            try {
                wait() ;
            } catch(InterruptedException e){ }
        }
        papers += paperstack;
        System.out.println("RefillPaper, paper with refilled: " + papers);
        notifyAll();

    }

    public synchronized void refilltoner(int tonerSize) {
        while ( maxpages()<currentToner || maxpages()==-1 )
        {
            try {
                wait() ;
            } catch(InterruptedException e){ }
        }
        currentToner += tonerSize;

        System.out.println("RefillToner, toner with refilled: " + currentToner);
        notifyAll();

    }

    public void addDocumentToBuffer(Document doc) {
        document_buffer.add(doc);
    }


    public int maxpages() {
        int max = -1;
        for (int i=0;i<document_buffer.size(); i++){
            if (i == 0) {
                max = document_buffer.get(0).getPages();
            } else if (document_buffer.get(i).getPages() > max) {
                max = document_buffer.get(i).getPages();
            }
        }
        return max;
    }




}
