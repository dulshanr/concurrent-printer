package normal;

public class TwoThreadsTest {
    public static void main(String[] args) {

        Thread secondThrd;
        Thread firstThrd;

        firstThrd = new SimpleThread("FirstThread");
        secondThrd = new SimpleThread("SecondThread");

        firstThrd.start();
        secondThrd.start();
    }
}
