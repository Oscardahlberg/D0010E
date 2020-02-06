
/**
 * Skriven av Oscar Dahlberg för kurs D0010E, LTU, bonus uppgift 7
 * @author Oscar Dahlberg
 * Syftet med detta program är att skapa en väska som kan fyllas med
 * en sort av innehåll, hålla reda på innehållet samt vikten av det.
 * Väskan kan bara vara fyllt med en sort innehåll. Den håller även
 * reda på om väskan är fylld eller inte.
 */

public class Bag <E> implements Weighable{

    private boolean bagFull = false;
    private int bagWeight = 0;
    private E content = null;

    public void fillBagWith(E content, int weight){
        //Fyller väskan med content av typen E, och gör så att innehållet väger weight.
        if(this.content == null && !this.bagFull){
            //Väskan får inte ha något innehåll och måste vara tom för
            //att kunna fylla den med nya saker
            this.content = content;
            this.bagWeight = weight;
            this.bagFull = true;
        }
    }

    public Pair<E, Integer> emptyBag(){

        Pair<E, Integer> weightAndContent = new Pair<E, Integer>(this.content, this.weight());
        //Returnerar innehållet i väskan och sätter innehållet
        //till null samt vikten till noll och deklarerar att väskan inte är full
        //Vilket betyder att den kan fyllas igen

        this.content = null;
        this.bagWeight = 0;
        this.bagFull = false;

        return weightAndContent;
    }

    @Override
    public int weight() {
        //Returnerar vikten av väskan
        return bagWeight;
    }
}
