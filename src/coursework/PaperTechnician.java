package coursework;

public class PaperTechnician extends Thread {

    private PrinterMonitor printer;
    private String name;
    private int attempts;

    public PaperTechnician(PrinterMonitor printer,ThreadGroup threadGroup,String name, int attempts) {
        super(threadGroup,name);
        this.printer = printer;
        this.attempts = attempts;
        this.name = name;
    }


    public void run()
    {
        int i = 0;
        while (printer.getFinishedCount()<printer.getTotalStudentThreads() && i<attempts) {
            printer.refillPaper();
            try {
                sleep(generateRandomNumber(1,5000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }

    }
    public int generateRandomNumber(int min,int max) {
        return ((int) (Math.random() * (max - min)) + 1)+min;
    }
}
