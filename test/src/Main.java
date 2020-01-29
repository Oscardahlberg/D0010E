public class Labb1
{
    public static void main(String[] args)
    {
        Task1(42);
        Task2(42);
        Task3();
        Task4(42);
        //Do 6
    }
    public static void Task1(int n)
    {
        System.out.println(n);
        while(n>1)
        {
            System.out.println(LifeLength.f1(n));
            n = LifeLength.f1(n);
        }
    }
    public static void Task2(int n)
    {
        System.out.println("f1 =" + LifeLength.f1(n) +
                "\nf2 = " + LifeLength.f2(n) +
                "\nf4 = " + LifeLength.f4(n) +
                "\nf8 = " + LifeLength.f8(n) +
                "\nf16 = " + LifeLength.f16(n) +
                "\nf32 = " + LifeLength.f32(n));
    }
    public static void Task3()
    {
        System.out.println(LifeLength.iterateF(3, 5));
        System.out.println(LifeLength.iterateF(42, 3));
        System.out.println(LifeLength.iterateF(1, 3));
    }
    public static void Task4(int n)
    {
        System.out.println(LifeLength.intsToString(n));
    }
    /*public static void Task8(double x, int k)
    {
        System.out.println("recraisehalf = " + Raise.recRaiseHalf(x, k));
    }*/
}

class LifeLength
{
    public static int f1(int a0)
    {
        if(a0 == 1)
            return 1;
        else if(a0%2==0)
            return a0/2;
        else
            return 3*a0+1;

    }
    public static int f2(int a0)
    {
        return f1(f1(a0));
    }
    public static int f4(int a0)
    {
        return f2(f2(a0));
    }
    public static int f8(int a0)
    {
        return f4(f4(a0));
    }
    public static int f16(int a0)
    {
        return f8(f8(a0));
    }
    public static int f32(int a0)
    {
        return f16(f16(a0));
    }
    public static int iterateF(int a0, int n)
    {
        int sum = a0;
        for(int i = 0; i<n; i++)
        {
            sum = f1(sum);
        }
        return sum;
    }
    public static int iterLifeLength(int a0)
    {
        int count = 0;
        int n = a0;
        while(n>1)
        {
            n = f1(n);
            count++;
        }
        return count;
    }
    public static String intsToString(int a0)
    {
        return "The life length of " + a0 + " is " + iterLifeLength(a0) + ".";

    }
}


class Raise
{
    //How did this happen?
}
