package View;


import java.util.Observable;
import java.util.Observer;

public interface View extends Observer {

    @Override
    void update(Observable o, Object arg);

}
