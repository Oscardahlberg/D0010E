import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author Oscar Dahlberg
 *
 * Created by Oscar Dahlberg for course D0010E at LTU for BU13
 *
 */

public class Shuffled<E> {

    private E[] arr;
    private Random randomNum;

    /**
     *
     * @param arr
     * @param seed
     * The constructor takes an array and a long and creates an internal
     * array and a object of typ Random with the long.
     *
     */

    Shuffled(E[] arr, long seed){
        this.arr = arr;
        this.randomNum = new Random(seed);
    }

    /**
     *
     * Uses the array created in the constructor to shuffle the
     * types which exist on the different indices. The random
     * 'shuffle' is based on the long seed the user inputed.
     *
     * It then returns the shuffled array.
     *
     */

    public E[] next(){

        //Creates a variable that will be used to remember
        //what variable was in a specific index in the array
        E tempE;

        //Used to create a random number between (and including)
        //0 and between (but not including) length of the array - 1
        int random;

        //Is true when the int random is a value between
        //(and including) 0 and between (but not including)
        // length of the array - 1
        Boolean isRandomCorrect;

        for(int i = arr.length; i > 0; i--){

            random = Math.abs(randomNum.nextInt());
            isRandomCorrect = false;

            // Continues until random is a number between
            // (and including) 0 and between (but not including)
            // length of the array - 1
            while(!isRandomCorrect){
                random = Math.abs(randomNum.nextInt());
                random = random - (random / 10) * 10;

                if(random <= arr.length - 1){
                    isRandomCorrect = true;
                }
            }

            tempE = arr[i - 1];
            arr[i - 1] = arr[random];
            arr[random] = tempE;
        }

        return arr;
    }


    public static void main (String[] args){

        int[] a = { 1, 2, 3, 4, 5 };
        Integer[] ia = new Integer[a.length];
        for (int i = 0; i < a.length; i++) {
            ia[i] = a[i];
        }

        Shuffled<Integer> e = new Shuffled<>(ia, System.currentTimeMillis());
        System.out.println(Arrays.toString(e.next()));
    }
}
