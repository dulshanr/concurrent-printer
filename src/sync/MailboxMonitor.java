package sync;

class MailboxMonitor implements Mailbox
{
    private int contents;
    private boolean available = false;
    public synchronized int take( )
    {
        while ( !available )
        {
            try {
                wait() ;
            } catch(InterruptedException e){ }
        }
        available = false ;
        System.out.println(" take: " + contents);
        tehe();
        notifyAll() ; // signal change of state
        return contents ;
    }
    public synchronized void tehe( )
    {
        System.out.println("Tehe : after take");
    }
    public synchronized void put( int value )
    {
        while ( available )
        {
            try {
                wait() ;
            } catch(InterruptedException e){ }
        }
        contents = value ;
        available = true ;
        System.out.println(" put: " + contents);
        notifyAll() ; // signal change of state
    }
} // MailboxMonitor


