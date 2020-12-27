package unsync;

class Consumer extends Thread
{
    private final Mailbox mailbox ; // Mailbox "interface"
    private final int numberOfItems ;
    public Consumer( Mailbox mailbox, int Cid, int numberOfItems )
    {
        super( "Consumer #" + Cid ) ; // Thread( thrd_name )
        this.mailbox = mailbox ;
        this.numberOfItems = numberOfItems ;
    }
    public void run()
    {
        int value = 0 ;
        for (int i = 0; i < numberOfItems; i++)
        {
            value = mailbox.take() ;
            System.out.println(getName() + " taken: " + value) ;
        }
    }
}
