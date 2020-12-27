package unsync;

class ProdConSimpleMailbox
{
    public static void main( String args[] )
    {
        final int NUMBITEMS = 10 ;
        Mailbox smb = new SimpleMailbox() ;
        Producer p1 = new Producer( smb, 1, NUMBITEMS ) ;
        Consumer c1 = new Consumer( smb, 1, NUMBITEMS ) ;
        p1.start() ;
        c1.start() ;
    }
}

