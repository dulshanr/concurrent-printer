package unsync;

interface Mailbox
{
    // put an item into the mailbox
    public void put( int value ) ;
    // take (i.e. remove & return) an item
// from the mailbox
    public int take( ) ;
}

