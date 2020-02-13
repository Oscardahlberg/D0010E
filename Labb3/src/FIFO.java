import java.util.ArrayList;
import java.util.NoSuchElementException;

public class FIFO implements Queue {

    private ArrayList<Object> queue = new ArrayList<Object>();
    private int maxLength = 0;

    public boolean isEmpty(){
        return this.queue.size() == 0;
    }

    public void removeFirst(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        this.queue.remove(0);
    }

    public Object first(){
        if(isEmpty()){
            throw new NoSuchElementException();
        }
        return this.queue.get(0);
    }

    public int maxSize(){
        return maxLength;
    }

    public void add(Object item){
        this.queue.add(item);
        if(size() < this.queue.size()){
            maxLength = this.queue.size();
        }
    }

    public int size(){
        return this.queue.size();
    }

    public String toString(){

        String s = "Queue: ";
        for(Object elem : this.queue){
            s += "(" + String.valueOf(elem) + ") ";
        }
        return s;
    }

    public boolean equals(Object f){
        if(f.getClass() != this.getClass()){
            throw new ClassCastException();
        }

        return ((FIFO) f).size() == this.size() && this.haveEqualRefs(f);
    }

    private boolean haveEqualRefs(Object f){

        for(int i = 0; i < this.queue.size(); i++){

            if(this.queue.get(i) == null && ((FIFO)f).queue.get(i) != null){
                return false;
            }

            if(this.queue.get(i) != null ){
                if(!this.queue.get(i).equals( ((FIFO)f).queue.get(i) )){
                    return false;
                }
            }
        }
        return true;
    }
}