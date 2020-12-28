package coursework;

public class TonerTechnician extends Thread {
    private PrinterMonitor printer;
    private int paperCartridge;
    private int attempts;

    public TonerTechnician(PrinterMonitor printer, int paperCartridge, int attempts) {
        this.printer = printer;
        this.paperCartridge = paperCartridge;
        this.attempts = attempts;
    }

    public PrinterMonitor getPrinter() {
        return printer;
    }

    public int getPaperCartridge() {
        return paperCartridge;
    }


    public int getAttempts() {
        return attempts;
    }

    public void run()
    {
//        for (int i = 0; i < attempts; i++)
//        {
//            printer.refilltoner(20);
//            try {
//                sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        int i = 0;
        while (printer.getFinishedcount()<4 && i<3) {
            printer.refilltoner(20);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
    }

}
