import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * Coded by Oscar Dahlberg for course D0010E at LTU for BU8
 *
 */

public class String2intArray {

    public static int[] str2ia(String s) {

        Scanner scan = new Scanner(s);
        scan.useDelimiter(" *, *");

        int[] intArray = new int[0];

        try {

            boolean isCorrectlyFormated = true;
            //Gör en char array som är lika lång som antalet karaktärer i strängen s
            char[] characterArray = new char[s.length()];

            if(s.length() != 0){

                //Kollar så att strängen är korrekt formaterad

                //Sätter in varje karaktär från strängen in i en char array
                for (int i = 0; i < s.length(); i++) {
                    characterArray[i] = s.charAt(i);
                }

                //Kollar så att de första och sista karaktärerna är nummer
                if (!Character.isDigit(characterArray[0]) ||
                        !Character.isDigit(characterArray[characterArray.length - 1])) {
                    isCorrectlyFormated = false;
                }

                //Räknar mellanslag
                int spaceCounter = 0;

                //För att komma ihåg om det kommit ett tal redan, så att det
                //inte kommer flera tal per komma tecken ex. ,5 5,
                boolean oneNumber = false;

                for (char i : characterArray) {

                    //Antalet mellanslag och om det kommit ett nummer ska
                    //återställas när det kommer ett komma
                    if (i == ',') {
                        spaceCounter = 0;
                        oneNumber = false;
                    }

                    //Kollar så att det inte kommer ett nummer, mellanslag och sen ett till nummer
                    if (Character.isDigit(i)) {
                        if (!oneNumber) {
                            oneNumber = true;
                        } else {
                            isCorrectlyFormated = false;
                        }
                    }

                    //Kollar så att alla karaktärer är antingen ett nummer, ett mellanslag eller ett komma tecken
                    if (!Character.isDigit(i)
                            && i != ' '
                            && i != ',') {
                        isCorrectlyFormated = false;
                    }

                    if (i == ' ') {
                        spaceCounter += 1;
                    } else {
                        oneNumber = false;
                    }

                    //Kollar så att det bara finns ett mellanslag per komma tecken
                    if (spaceCounter > 1) {
                        isCorrectlyFormated = false;
                    }
                }
            }

            //Kastar ett error om strängen inte är korrekt formaterad
            if (!isCorrectlyFormated) {
                throw new IllegalArgumentException();
            }

            ArrayList<Integer> temp = new ArrayList<Integer>();

            //Lägger in varje tal i en array list som tar slut när det inte finns
            //några mer nummer
            try {
                while (true) {

                    String nextString = scan.next();

                    temp.add(Integer.parseInt(nextString));
                }
            } catch (NoSuchElementException e) {

                //lägger in alla tal från en array list in i en array
                intArray = new int[temp.size()];
                for(int i = 0; i < temp.size(); i++){
                    intArray[i] = temp.get(i);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e);
        }

        return intArray;
    }
}
