package include.Shops;

public class Careffour extends Shop_Base
{

    public Careffour()
    {
        super();
    }

    @Override
    public void marketplace_strategy() //do every month
    {
        //buys big amount of cargos
        //first, sell cargos in queue, if possible
        market_resuply(1000);
    }
    
}
