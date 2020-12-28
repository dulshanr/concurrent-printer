package coursework;

import java.util.ArrayList;

public class PrinterMonitor {

    private int papers = 100;
    private int maxPapers = 100;
    private int currentToner = 90;
       private ArrayList<Document> document_buffer = new ArrayList<>();




    public synchronized void print(Document document,String student_label) {

        while ( (document.getPages()>currentToner) || (document.getPages()>papers) || document_buffer.size() == 0)
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

    public synchronized void addDocument(Document document) {
        while (document_buffer.size() != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        document_buffer.add(document);
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
        while ( (minpages()<currentToner || minpages()==-1) && currentToner>10)
        {
            try {
                wait() ;
            } catch(InterruptedException e){ }
        }
        currentToner += tonerSize;

        System.out.println("RefillToner, toner with refilled: " + currentToner);
        notifyAll();

    }



    public int minpages() {
        int min = -1;
        for (int i=0;i<document_buffer.size(); i++){
            if (i == 0) {
                min = document_buffer.get(0).getPages();
            } else if (document_buffer.get(i).getPages() < min) {
                min = document_buffer.get(i).getPages();
            }
        }
        return min;
    }

//    public void displayList() {
//        System.out.println("Total No: "+document_buffer.size());
//    }




}
