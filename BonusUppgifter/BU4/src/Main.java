import java.util.Scanner;

//Created by Oscar Dahlberg for course D0010E at LTU

public class Main {

    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        Stopwatch x = new Stopwatch();
        boolean running = true;

        while(running){

            System.out.println("What do you want to do?");
            System.out.println("1. Check time");
            System.out.println("2. Start stopwatch");
            System.out.println("3. Stop stopwatch");
            System.out.println("4. Reset stopwatch");
            System.out.println("5. End");

            try{
                int userChoice = Integer.parseInt(input.nextLine());
                switch(userChoice){

                    case 1:
                        System.out.println();
                        System.out.println(x.time());
                        System.out.println();
                        break;
                    case 2:
                        x.start();
                        break;
                    case 3:
                        x.stop();
                        break;
                    case 4:
                        x.reset();
                        break;
                    case 5:
                        running = false;
                        break;
                    default:
                        System.out.println("Retype please");

                }
            }

            catch(NumberFormatException e){
                System.out.println("Retype please");
            }
        }
    }
}
