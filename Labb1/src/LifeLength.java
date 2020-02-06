class LifeLength {
    static int f1(int a0){
        int a1;
        if (a0 == 1) {
            a1 = 1;
        }
        else if(a0 % 2 == 0) {
            a0 = a0 / 2;
            a1 = a0;
        }
        else {
            a0 = 3*a0 + 1;
            a1 = a0;
        }
        return a1;
    }

    static int f2(int a0) {
        return f1(f1(a0));
    }
    static int f4(int a0) {
        return f2(f2(a0));
    }
    static int f8(int a0) {
        return f4(f4(a0));
    }
    static int f16(int a0) {
        return f8(f8(a0));
    }
    static int f32(int a0) {
        return f16(f16(a0));
    }

    static int iterateF(int a0, int n){
        int an = 0;
        for(int i = 1; i <= n; i++){
            a0 = f1(a0);
            an = a0;
        }
        return an;
    }

    public static int iterLifeLength(int a0){
        int y = 1;
        while(a0 > 1){
            a0 = f1(a0);
            y++;
        }
        return y;
    }

    static String intsToString(int x){
        int y = iterLifeLength(f1(x));
        return "(Iter)The life length of " + x + " is " + y + ".";
    }

    private static int recLifeLength(int a0){
        if(a0 == 1){
            return 1;
        }
        else{
            return recLifeLength(f1(a0)) + 1;
        }
    }
    static String intsToStringRec(int x){
        int y = recLifeLength(f1(x));
        return "(Rec)The life length of " + x + " is " + y + ".";
    }
}
