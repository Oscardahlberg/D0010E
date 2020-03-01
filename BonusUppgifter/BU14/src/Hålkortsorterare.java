import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Oscar Dahlberg
 *
 * Created by Oscar Dahlberg for course D0010E at LTU for BU14
 *
 * This program is used to sort an array
 * of integers from smallest to biggest.
 *
 */

public class Hålkortsorterare {

    private LinkedList<Integer>[] pall = new LinkedList[10];

    /**
     *
     * The constructor puts 10 LinkedLists into the array pall
     *
     */

    Hålkortsorterare(){
        for(int i = 0; i < 10; i++)
        pall[i] = new LinkedList<Integer>();
    }

    /**
     *
     * Takes an array of integers and sort them from smallest to biggest
     *
     * It does this by always taking the last digit of a number
     * and putting it in a specific linked list. Then they are put
     * into the hög. But this has only sorted them according to the last
     * digit, so we divide it by 10 and then do it again for the last digit.
     * And so on.
     *
     */

    public void sortera(int[] hög){

        int numOfDigits = 1;

        // The loop runs as many times as a integer in hög has digits
        while(hög[0] / Math.pow(10, numOfDigits - 1) > 1){

            // Puts all of the integers from hög into a specific linked list
            // according to the last digit.
            // After the first loop it will divide the number by 10 and do the same thing
            for(int i : hög){
                int y = (i % (int) Math.pow(10, numOfDigits)) / (int) Math.pow(10, numOfDigits - 1);
                pall[y].add(i);
            }

            int högIndex = 0;

            // Removes all of the numbers in the linked list and puts them
            // back into the array hög
            for(int i = 0; i < pall.length; i++){
                for(int y = 0; y < pall[i].size();){
                    hög[högIndex] =  pall[i].poll();
                    högIndex++;
                }
            }

            numOfDigits++;
        }
    }

    public static void main (String[] args){

        int[] hög = new int[]{29, 11, 32, 53, 44, 35, 66, 17, 98, 99};

        Hålkortsorterare e = new Hålkortsorterare();
        e.sortera(hög);

    }

}
