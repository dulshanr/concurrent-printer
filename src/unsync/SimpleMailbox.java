package unsync;

class SimpleMailbox implements Mailbox
{
    private int contents = 0 ;
    public void put( int value )
    {
        contents = value ;
    }
    public int take()
    {
        return contents ;
    }
}
