public class Main {


    public static void main(String[] args) {

        int[] result = String2intArray.str2ia("5 , 5");

        System.out.println(result.length);

        for(int i : result){
            System.out.println(i);
        }
    }
}
