package include.Clients;


public class Client 
{

    private int client_age;
    private boolean in_buy;
    private int day_of_buy;
    private int amount_to_buy;
    
    public Client(int client_age)
    {
        this.client_age = client_age;
        this.in_buy = false;
        this.day_of_buy = 0;
        this.amount_to_buy = 0;
    }



    public int getAmount_to_buy() {
        return amount_to_buy;
    }


    public void setAmount_to_buy(int amount_to_buy) {
        this.amount_to_buy = amount_to_buy;
    }

    public int getDay_of_buy() 
    {
        return day_of_buy;
    }

    public void setDay_of_buy(int day_of_buy) 
    {
        this.day_of_buy = day_of_buy;
    }


    public boolean getIn_buy() 
    {
        return in_buy;
    }

    public void setIn_buy(boolean in_buy) 
    {
        this.in_buy = in_buy;
    }

    public void setClient_age(int client_age) 
    {
        this.client_age = client_age;
    }
    
    public int getClient_age() 
    {
        return client_age;
    }
}
