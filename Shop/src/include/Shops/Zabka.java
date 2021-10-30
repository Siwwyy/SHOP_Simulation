package include.Shops;

public class Zabka extends Shop_Base
{

    public Zabka()
    {
        super();
    }

    @Override
    public void marketplace_strategy() 
    {
        //buys small amount of cargos
        market_resuply(10);
    }
    
}
