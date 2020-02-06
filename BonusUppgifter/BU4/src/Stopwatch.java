import java.util.Scanner;

/**
 * Skapad av Oscar Dahlberg 970314-6994 för LTU, bonus uppgift 4
 */

class Stopwatch {

    private static Scanner input = new Scanner(System.in);

    private boolean isRunning = false;
    private double time = 0;
    private long recordTime = 0;

    public static void main(String[] args) {

        Stopwatch x = new Stopwatch();
        boolean running = true;

        while (running) {

            System.out.println("What do you want to do?");
            System.out.println("1. Check time");
            System.out.println("2. Start stopwatch");
            System.out.println("3. Stop stopwatch");
            System.out.println("4. Reset stopwatch");
            System.out.println("5. End");

            try {
                int userChoice = Integer.parseInt(input.nextLine());
                switch (userChoice) {

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
            } catch (NumberFormatException e) {
                System.out.println("Retype please");
            }
        }
    }

    public void start(){
        if(!isStarted()){
            isRunning = true;
            recordTime = System.currentTimeMillis();
        }
    }

    public void stop(){
        if(isStarted()){
            isRunning = false;
            time += (System.currentTimeMillis() - recordTime) / 1000;
        }
    }

    public boolean isStarted(){
        return isRunning;
    }

    public void reset(){
        time = 0;

        if(isStarted()){
            //Om isStarted är true så behövs bara tiden sättas till 0
            //Och den ska börja räkna igen
            recordTime = System.currentTimeMillis();
        }
        else{
            //Det här är för om man vill kolla tiden i metoden time()
            //Så ska den inte börja räkna
            recordTime = 0;
        }
    }

    public double time(){

        if(time == 0 && recordTime == 0){
            //Om stopwatch är stannad och resettad, vill jag inte att
            //den börjar köra igen om man kollar på tiden
            return 0;
        }

        if(isStarted()){
            //Om isStarted är true så måste vi lägga på tiden som har gått +
            //börja räkna tiden igen
            double tempTimeSince;
            tempTimeSince = System.currentTimeMillis() - recordTime;
            time += tempTimeSince / 1000;
            recordTime = System.currentTimeMillis();
        }

        return Math.rint(time * 10.0d) / 10.0d;
    }
}
