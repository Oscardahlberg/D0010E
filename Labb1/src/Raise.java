public class Raise {

    private static int recOneLoops = -1;
    private static int recHalfLoops = -1;

    private static double recRaiseOne(double x, int k) {
        recOneLoops += 1;
        if (k==0) {
            return 1.0;
        } else {
            return x * recRaiseOne (x, k-1);
        }
    }

    private static double recRaiseHalf(double x, int k){
        recHalfLoops += 1;
        if(k == 0){
            return 1;
        }
        else if(k % 2 == 0){
            double rec = recRaiseHalf(x,k/2);

            return rec * rec;
        }
        else{
            double rec = recRaiseHalf(x,k/2);

            return x * rec * rec;
        }
    }

    public static void main(String[] args){
        double x = 1.000001;
        int k = 15;

        //System.out.println("RecRaiseHalf: " + x + "^" + 75000 + " = " + recRaiseHalf(x, 75000) + ", recursive calls: " + recHalfLoops);

        for(int i = 1; i <= k; i++){
            System.out.println("RecRaiseOne: " + x + "^" + i + " = " + recRaiseOne(x, i) + ", recursive calls: " + recOneLoops);
            System.out.println("RecRaiseHalf: " + x + "^" + i + " = " + recRaiseHalf(x, i) + ", recursive calls: " + recHalfLoops);
            System.out.println(" ");

            recHalfLoops = -1;
            recOneLoops = -1;
        }
    }
}