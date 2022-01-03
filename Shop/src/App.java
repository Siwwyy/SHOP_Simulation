import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import include.Clients.Client;
import include.Shops.Careffour;
import include.Shops.Lidl;
import include.Shops.Shop_Base;
import include.Shops.Zabka;
import include.Utils.Math_Utils;

public class App 
{
    public static void main(String[] args) throws Exception 
    {

        // Shop_Base[] shops = { new Lidl(), new Careffour(), new Zabka() };

        // float credit_cost = 1.02f;
        // for(int i = 1; i < 1000; ++i)
        // {
        //     if(i % 30 == 0)
        //     {
        //         for(int j = 0; j < shops.length; ++j)
        //         {

        //         }
        //     }
        // }


        //Simulation below works
        // Shop_Base hehe = new Lidl();
        // for(int i = 0; i < 1000; ++i)
        // {
         
        //     if(i % 10 == 0)
        //     {
        //         float credit_cost = 1.02f;
        //         hehe.marketplace_strategy(); //do resuply and other stuffs every 10 days
        //         hehe.credit_increase(credit_cost);
        //     }
        //     hehe.print_shop();
        //     hehe.market_sell("cargo1", 300);
            
         
        //     System.out.println("\n============================\n");
        //     Thread.sleep(400);
        // }

        //////////////////////////////////////////////////////////////////////

        //Init simulation
        //Init shops
        Shop_Base[] shops = { new Lidl(), new Careffour(), new Zabka() };
        //Init clients group
        int client_amount = 100;
        int cargo_amount = shops[0].getShop_capacity();
        List<Client>[] clients_group = new List[cargo_amount];
        float[] lambas_exp = Math_Utils.linspace(15, 4, 8);          //create lambdas for exp 
        float[] lambas_poisson = Math_Utils.linspace(20, 60, 8);     //create lambdas for poisson

        for (int i = 0; i < cargo_amount; i++) 
        {
            clients_group[i] = new ArrayList<Client>();
        }

        for(int i = 0; i < client_amount; ++i)
        {
            int client_age = Math_Utils.get_client_age();   //draw lots randomly a client age (wylosuj)
            int group_idx = ((client_age % 100) / 10) - 2;  //100 because max % max == 0! (where max age is 99, so number one bigger than 99!)
            // System.out.println("Age: " + client_age + " group idx: " + group_idx);
            clients_group[group_idx].add(new Client(client_age));
        }


        // for(int i = 0; i < clients_group.length; ++i)
        // {
        //     for(int j = 0; j < clients_group[i].size(); ++j)
        //     {
        //         System.out.println("Age: " + clients_group[i].get(j).getClient_age() + " group idx: " + i);
        //     }
    
        // }

        String[] cargo_names = {
            "cargo1", //1
            "cargo2", //2
            "cargo3", //3
            "cargo4", //4
            "cargo5", //5
            "cargo6", //6
            "cargo7", //7
            "cargo8", //8
        };

        Random r = new Random();

        int day_amount = 365; //12 months
        while(day_amount > 0)
        {
            if(day_amount % 10 == 0)
            {
                float credit_cost = 1.02f;
                for(int i = 0; i < shops.length; ++i)
                {
                    shops[i].marketplace_strategy(); //do resuply and other stuffs every 10 days
                    shops[i].credit_increase(credit_cost); //increase credit
                }

                for(int k = 0; k < shops.length; ++k)
                {
                    shops[k].print_shop();
                }
                System.out.println("\n============================\n");
            }
            
         
            //Time of buy for clients
            for(int i = 0; i < clients_group.length; ++i)
            {
                for(int j = 0; j < clients_group[i].size(); ++j)
                {
                    if(clients_group[i].get(j).getIn_buy() == false)
                    {
                        //draw lots a day of buy for specified client
                        int day_of_buy = (int) Math_Utils.getExp(r, lambas_exp[i]);
                        clients_group[i].get(j).setDay_of_buy(day_of_buy);
                        clients_group[i].get(j).setIn_buy(true);

                        //draw lots a amount of cargo to buy
                        int amount_to_buy = Math_Utils.getPoisson(r, lambas_poisson[i]);
                        clients_group[i].get(j).setAmount_to_buy(amount_to_buy);
                    }


                    //Moment of buying
                    if(clients_group[i].get(j).getIn_buy() == true && clients_group[i].get(j).getDay_of_buy() == day_amount)
                    {
                        String cargo_name = cargo_names[i];
                        for(int k = 0; k < shops.length; ++k)
                        {
                            shops[k].market_sell(cargo_name, clients_group[i].get(j).getAmount_to_buy());
                        }
                        clients_group[i].get(j).setIn_buy(false);
                    }
                }
            }


            Thread.sleep(400);
            --day_amount;
        }

        System.out.println("\n============================\n");
        System.out.println("\n============================\n");
        System.out.println("\n============================\n");
        for(int k = 0; k < shops.length; ++k)
        {
            shops[k].print_shop();
        }
        System.out.println("\n============================\n");

    }
}
