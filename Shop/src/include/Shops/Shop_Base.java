package include.Shops;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import include.Cargo;
import include.Utils.Math_Utils;




public abstract class Shop_Base 
{
    private int shop_capacity;
    private float shop_credit;
    private final float shop_margin = 1.2f;
    List <Cargo> cargo_list;
    Queue<Cargo> cargo_queue;



    private void init_cargo_list()
    {
        // List<Pair<String, Integer>> cargo_pair = new ArrayList<Pair<String, Integer>>();
        // cargo_pair.add(new Pair<String, Integer>("Apple", 10)); //1
        // cargo_pair.add(new Pair<String, Integer>("Banana", 10)); //2
        // cargo_pair.add(new Pair<String, Integer>("Water", 10)); //3
        // cargo_pair.add(new Pair<String, Integer>("Wood", 10)); //4
        // cargo_pair.add(new Pair<String, Integer>("Meat", 10)); //5
        // cargo_pair.add(new Pair<String, Integer>("Cham", 10)); //6
        // cargo_pair.add(new Pair<String, Integer>("Cucumber", 10)); //7

        // String[] cargo_names = {
        //     "Apple", //1
        //     "Banana", //2
        //     "Water", //3
        //     "Wood", //4
        //     "Meat", //5
        //     "Cham", //6
        //     "Cucumber", //7
        //     "Guarana", //8
        // };

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

        float[] prices = Math_Utils.linspace(80, 2000, this.shop_capacity);

        for(int i = 0; i < this.shop_capacity; ++i)
        {
            this.cargo_list.add(i, new Cargo(i+1, 0, prices[i], cargo_names[i]));
            // System.out.println(this.cargo_list.get(i));
        }
    }

    public Shop_Base()
    {
        this.shop_capacity = 8;
        this.shop_credit = 0.0f;
        this.cargo_list = new ArrayList<Cargo>(this.shop_capacity);
        this.cargo_queue = new LinkedList<Cargo>();
        init_cargo_list();
    }

    public void increase_credit(float cargo_price, float amount)
    {
        float income = cargo_price * amount;
        this.shop_credit += income;
    }

    public void decrease_credit(float cargo_price, float amount)
    {   
        float debit = cargo_price * amount;
        this.shop_credit -= debit;
    }

    public void market_resuply(int new_amount)
    {
        for (Cargo cargo: this.cargo_list) 
        {
            decrease_credit(cargo.getCargo_price(), new_amount);
            cargo.setCargo_amount(new_amount);
        }
    }

    public void market_sell(String cargo_name, int amount)
    {
        for (Cargo cargo: this.cargo_list) 
        {
            if(cargo.getCargo_name() == cargo_name)
            {
                int new_cargo_amount = cargo.getCargo_amount() - amount;
                if(new_cargo_amount < 0)
                {
                    //If new amount is less than 0, then add that product to queue
                    //and wait for resuply
                }
                else
                {
                    cargo.setCargo_amount(new_cargo_amount);
                }
                break;
            }

        }
    }

    public void market_sell_queue()
    {

    }

    public abstract void marketplace_strategy();

    public void setShop_credit(float shop_credit) 
    {
        this.shop_credit = shop_credit;
    }
    
    public float getShop_credit() 
    {
        return shop_credit;
    }

    public int getShop_capacity()
    {
        return shop_capacity;
    }

    public float getShop_margin() 
    {
        return shop_margin;
    }
}



