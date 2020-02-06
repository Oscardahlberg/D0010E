
/**
 * @author hj
 */
public class Pair<A, B> {
    private A first;
    private B second;

    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public A fst() {
        return first;
    }

    public B snd() {
        return second;
    }

    public Pair<B, A> mirror() {
        return new Pair<B, A>(snd(), fst());
    }

    public String toString() {
        return "(" + fst() + "," + snd() + ")";
    }

}