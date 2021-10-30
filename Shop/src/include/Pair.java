package include;

public class Pair<L,R> 
{
    private L left;
    private R right;

    public Pair(L left, R right)
    {
        this.left = left;
        this.right = right;
    }

    public void setleft(L left)
    { 
        this.left = left;
    }

    public void setR(R right)
    { 
        this.right = right;
    }

    public L getleft()
    { 
        return left;
    }
    public R getright()
    { 
        return right; 
    }

}