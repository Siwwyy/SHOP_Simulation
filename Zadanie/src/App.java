import include.Shops.Careffour;
import include.Shops.Lidl;
import include.Shops.Shop_Base;
import include.Shops.Zabka;

public class App 
{
    public static void main(String[] args) throws Exception 
    {
        Shop_Base[] shops = { new Lidl(), new Careffour(), new Zabka() };

        float credit_cost = 1.02f;
        for(int i = 1; i < 1000; ++i)
        {
            if(i % 30 == 0)
            {
                for(int j = 0; j < shops.length; ++j)
                {

                }
            }
        }

    }
}
