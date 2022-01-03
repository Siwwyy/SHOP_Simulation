package include.Utils;

import java.util.Random;

public class Math_Utils
{
    
    public static float[] linspace(float start, float end, int steps)
    {
        float[] linear_space;
        if(steps <= 1)
        {
            steps = 1;
            linear_space = new float[steps];
            linear_space[0] = start;
        }
        else
        {
            linear_space = new float[steps];
            int range = steps;
    
            steps = (int) ((end - start) / (range - 1));
            linear_space[0] = start;
            for(int i = 1; i < range - 1; ++i)
            {
                linear_space[i] = start + (float)(steps * i);
            }
            linear_space[range - 1] = end;
        }
        return linear_space;
    }


    public static int getPoisson(Random rand, double lambda) 
    {
        double l = Math.exp(-lambda);
        double p = 1.0;
        int k = 0;
        do
        {
            k++;
            p *= rand.nextDouble();
        } 
        while (p > l);
        return k - 1;
    }

    public static double getExp(Random rand, double lambda) 
    {
        return -lambda*Math.log(1-rand.nextDouble());
    }

    public static int get_gaussian_distribution()
    {
        Random r = new Random();
        double myG = r.nextGaussian()*20+65;
        return (int)myG;
    }

    public static int get_client_age()
    {
        int age = get_gaussian_distribution();
        if(age < 20 || age > 99)
        {
            return get_client_age();
        }
        return age;
    }

}
