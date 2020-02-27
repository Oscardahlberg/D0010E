import java.util.Random;

public class Main {

    public static void main (String[] args){
        long seed = System.currentTimeMillis();
        Random randomNum = new Random(seed);
        int random = Math.abs(randomNum.nextInt());
        random = random - (random / 10) * 10;


        System.out.println(random);
        while(random > 10){
            random = random / 10;
        }

    }
}
