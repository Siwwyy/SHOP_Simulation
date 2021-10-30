package include.Shops;

public class Lidl extends Shop_Base
{


    public Lidl()
    {
        super();
    }

    @Override
    public void marketplace_strategy() 
    {
        //buys medium amount of cargos
        market_resuply(100);

    }
    
}
