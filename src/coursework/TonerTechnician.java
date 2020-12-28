package coursework;

public class TonerTechnician extends Thread {
    private final PrinterMonitor printer;
    private int attempts;
    private String name;

    public TonerTechnician(PrinterMonitor printer,ThreadGroup threadGroup,String name, int attempts) {
        super(threadGroup,name);
        this.printer = printer;
        this.attempts = attempts;
        this.name = name;
    }

    public void run()
    {
        int i = 0;
        while (printer.getFinishedCount()<printer.getTotalStudentThreads() && i<attempts) {
            printer.replaceTonerCartridge();
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
