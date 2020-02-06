public class Main {

    private static void taskOne(int a0){
        System.out.println(LifeLength.f1(a0));
    }
    private static void taskTwo(int a0){
        System.out.println("f2: " + LifeLength.f2(a0));
        System.out.println("f4: " + LifeLength.f4(a0));
        System.out.println("f8: " + LifeLength.f8(a0));
        System.out.println("f16: " + LifeLength.f16(a0));
        System.out.println("f32: " + LifeLength.f32(a0));
    }
    private static void taskThree(){
        System.out.println(LifeLength.iterateF(3,5));
        System.out.println(LifeLength.iterateF(42,3));
        System.out.println(LifeLength.iterateF(1,3));
    }
    private static void taskFourAndSix(){
        for(int i = 1; i <= 15; i++){
            System.out.print(LifeLength.intsToString(i));

            System.out.println(LifeLength.intsToStringRec(i));
        }
    }

    public static void main(String[] args){

        int first = Integer.parseInt(args[0]);
        int second = Integer.parseInt(args[1]);

        int n = 1;
        switch (n) {
            case 1:
                taskOne(first);
                break;
            case 2:
                taskTwo(second);
                break;
            case 3:
                taskThree();
                break;
            case 4:
                taskFourAndSix();
                break;
            case 6:
                taskFourAndSix();
                break;
            default:
                System.out.println("n has an invalid value");
        }
    }
}
