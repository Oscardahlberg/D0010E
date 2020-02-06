
/**
Bonus uppgift 2 på LTU, Oscar Dahlberg 970314-6994
*/

public class Temp {

    public static double f2c(double f){
        return (f-32) * 5/9;
    }

    public static void main(String[] args) {
        for(int i = 40; i >= -40; i -= 5){
            System.out.println(i + "*F is " + (Math.rint(f2c(i) * 10.0d) / 10.0d) + "*C");
        }
    }
}
