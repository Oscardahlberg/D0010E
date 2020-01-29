
/**
 * Skriven av Oscar Dahlberg för kurs D0010E, LTU, bonus uppgift 6
 * @author Oscar Dahlberg
 * Klassen An's syfte är att visa dimensioner för specifierad typ av papper
 * Dessa inkluderar A0, A1, A2, A3, A4, A5, A6
 * Endast skriv in nummret som du vill veta dimensionerna för
 */

public class An {

    private static int templ1;

    public static Pair<Integer, Integer> a(int n){

        int kn = 841;
        int ln = 1189;
        //Sätter start dimensioner, alltså dimensionerna för A0

        for(int i = 0; i < n; i++){
            //Den kör så många gånger som variabeln n är

            templ1 = ln;
            //Informationen i varibeln sparas temporärt eftersom att vi behöver
            //sparade informationen efter att den ändrats

            ln = kn;
            kn = templ1 / 2;
            //Ändrar dimensionerna enligt formel
        }

        Pair<Integer, Integer> pair = new Pair<Integer, Integer>(ln, kn);

        return pair;
    }
}
