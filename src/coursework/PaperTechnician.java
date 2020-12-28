package coursework;

public class PaperTechnician extends Thread {

    private PrinterMonitor printer;
    private int paperPacket;

    public PaperTechnician(PrinterMonitor printer, int paperPacket) {
        this.printer = printer;
        this.paperPacket = paperPacket;
    }

    public PrinterMonitor getPrinter() {
        return printer;
    }

    public int getPaperPacket() {
        return paperPacket;
    }

    public void setPaperPacket(int paperPacket) {
        this.paperPacket = paperPacket;
    }

    public void run()
    {
//        for (int i = 0; i < 3; i++)
//        {
//            printer.refillPaper(20);
//            try {
//                sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        int i = 0;
        while (printer.getFinishedcount()<4 && i<3) {
            printer.refillPaper(50);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }



    }
}
