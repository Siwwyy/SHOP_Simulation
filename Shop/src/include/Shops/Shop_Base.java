package include.Shops;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import include.Cargo;
import include.Pair;
import include.Utils.Math_Utils;




public abstract class Shop_Base 
{
    private int shop_capacity;
    private float shop_credit;
    private final float shop_margin = 1.2f;
    List <Cargo> cargo_list;
    Queue<Pair<Cargo, Integer>> cargo_queue;



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

        float min_price = 80.f;
        float max_price = 2000.f;
        float[] prices = Math_Utils.linspace(min_price, max_price, this.shop_capacity);

        for(int i = 0; i < this.shop_capacity; ++i)
        {
            this.cargo_list.add(i, new Cargo(i+1, 0, prices[i], cargo_names[i]));
        }
    }

    public Shop_Base()
    {
        this.shop_capacity = 1;
        this.shop_credit = 0.0f;
        this.cargo_list = new ArrayList<Cargo>(this.shop_capacity);
        this.cargo_queue = new LinkedList<Pair<Cargo, Integer>>();
        init_cargo_list();
    }

    public void increase_credit(float cargo_price, float amount)
    {
        float income = cargo_price * amount * this.shop_margin;
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
            int new_cargo_amount = cargo.getCargo_amount() + new_amount;
            cargo.setCargo_amount(new_cargo_amount);
        }
    }

    public void credit_increase(float credit_percentage)
    {
        float new_credit_amount = (this.shop_credit * credit_percentage) - this.shop_credit;
        this.shop_credit -= new_credit_amount;
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
                    this.cargo_queue.add(new Pair<Cargo, Integer>(cargo, amount));
                }
                else
                {
                    //maybe rethink, saving a client, who is waiting for that cargo, to keep it until first 
                    //person buy amount it wants
                    increase_credit(cargo.getCargo_price(), amount);
                    cargo.setCargo_amount(new_cargo_amount);
                }
                break;
            }

        }
    }

    public void print_shop()
    {
        System.out.println("Shop credit: " + this.shop_credit);
        for (Cargo cargo: this.cargo_list) 
        {
            System.out.println(cargo.toString());
        }
    }

    public void market_sell_queue()
    {
        if(this.cargo_queue.isEmpty())
        {
            return;
        }

        for(Pair<Cargo, Integer> cargo_pair_elem : this.cargo_queue)
        {
            for (Cargo cargo: this.cargo_list) 
            {
                if(cargo_pair_elem.getleft().getCargo_name() == cargo.getCargo_name())
                {
                    if(cargo_pair_elem.getright() <= cargo.getCargo_amount())
                    {
                        int amount = cargo_pair_elem.getright();
                        int new_cargo_amount = cargo.getCargo_amount() - amount;

                        increase_credit(cargo.getCargo_price(), amount);
                        cargo.setCargo_amount(new_cargo_amount);
                    }
                    break;
                }
            }
        }
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



