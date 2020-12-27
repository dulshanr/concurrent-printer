package Selfish;

class SelfishRunner extends Thread
{
    public int tick = 1 ;
    SelfishRunner( int id ){ super( "SelfishRunner-" + id ) ; }
    public void run()
    {
        while ( tick < 400000 )
        {
            tick++ ;
            if ( ( tick % 50000 ) == 0 )
            {
                System.out.println( getName() + ": tick = " + tick ) ;
            }
        }
    }
}
