package include;

public class Cargo {
    
    
    private int cargo_id = 0;
    private int cargo_amount = 0;
    private float cargo_price = 0.f;
    private String cargo_name = "Shit";

    public Cargo(int cargo_id, int cargo_amount, String cargo_name)
    {
        this.cargo_id = cargo_id;
        this.cargo_amount = cargo_amount;
        this.cargo_price = 0.f;
        this.cargo_name = cargo_name;
    }

    public Cargo(int cargo_id, int cargo_amount, float cargo_price, String cargo_name)
    {
        this.cargo_id = cargo_id;
        this.cargo_amount = cargo_amount;
        this.cargo_price = cargo_price;
        this.cargo_name = cargo_name;
    }


    public void setCargo_price(float cargo_price) {
        this.cargo_price = cargo_price;
    }


    public void setCargo_amount(int cargo_amount) 
    {
        this.cargo_amount = cargo_amount;
    }

    public int getCargo_id() 
    {
        return cargo_id;
    }

    public String getCargo_name()
    {
        return cargo_name;
    }

    public float getCargo_price() 
    {
        return cargo_price;
    }

    public int getCargo_amount() 
    {
        return cargo_amount;
    }


    @Override
    public String toString() 
    {
      return "Cargo id: " + String.valueOf(this.cargo_id) + 
      " | Cargo amount: " + String.valueOf(this.cargo_amount) +
      " | Cargo price: " + String.valueOf(this.cargo_price) +
      " | Cargo name: " + String.valueOf(this.cargo_name) + '\n';
    }
}