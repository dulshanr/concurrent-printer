package sync;

class ProdConMailboxMonitor
{
    public static void main( String args[] )
    {
        final int NUMBITEMS = 10 ;
        System.out.println("time slicing "+Runtime.getRuntime().availableProcessors());
// Create: MailboxMonitor, Producer & Consumer
        Mailbox mbm = new MailboxMonitor() ;
        Producer p1 = new Producer( mbm, 1, NUMBITEMS ) ;
        Consumer c1 = new Consumer( mbm, 1, NUMBITEMS ) ;
// Start Producer & Consumer
        p1.start() ;
        c1.start() ;
    }
}

