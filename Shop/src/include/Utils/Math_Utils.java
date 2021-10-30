package include.Utils;

public class Math_Utils
{
    
    public static float[] linspace(float start, float end, int steps)
    {
        float[] linear_space = new float[steps];
        int range = steps;

        steps = (int) ((end - start) / (range - 1));
        linear_space[0] = start;
        for(int i = 1; i < range - 1; ++i)
        {
            linear_space[i] = start + steps * i;
        }
        linear_space[range - 1] = end;
        return linear_space;
    }


}
