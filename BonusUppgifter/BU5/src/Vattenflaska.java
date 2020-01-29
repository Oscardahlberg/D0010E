
/**
 * Skapad av Oscar Dahlberg 970314-6994 för LTU, bonus uppgift 5
 */

public class Vattenflaska {

    private String agare;
    private int kapacitet;
    private int innehall;

    public Vattenflaska(String agare, int kapacitet){
        this.agare = agare;
        this.kapacitet = kapacitet;
    }

    public int kapacitet(){
        return kapacitet;
    }

    public int innehall(){
        return innehall;
    }

    public void fyllPa(int mangd){
        innehall += mangd;
        if(innehall() > kapacitet){
            //Om innehållet är mer än vad som får plats i flaskan så sätts
            //innehålls värdet till kapaciteten
            innehall = kapacitet;
        }
    }

    public void tomUt(int mangd){
        innehall -= mangd;
        if(innehall() < 0){
            //Om mer vatten ska tömmas ut än vad som finns i flaskan så sätts
            //innehålls värdet till 0
            innehall = 0;
        }
    }

    public String toString(){
        return agare + "s flaska kan innehålla " + kapacitet() + " ml och innehåller nu " + innehall() + " ml";
    }

    public static void main(String[] args) {
        Vattenflaska x = new Vattenflaska("Oscar" , 3000);
        x.fyllPa(4000);
        System.out.println(x.innehall);
        x.tomUt(4000);
        System.out.println(x.innehall);
        System.out.println(x.toString());
    }

}