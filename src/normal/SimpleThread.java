package normal;

public class SimpleThread extends Thread {
    final int OneSecond = 1000;

    public SimpleThread(String str) {
        super(str); // ‘‘Thread( String )’’ constructor
    }

    public void run() // ‘‘body’’ of the thread
    {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + ": " + i);
            try {
                sleep((int) (Math.random() * OneSecond));
            } catch (InterruptedException e) {
                System.out.println("WOKEN");
            }
        }
        System.out.println(getName() + ": TERMINATING");

    }
}
