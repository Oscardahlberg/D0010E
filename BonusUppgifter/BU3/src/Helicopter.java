
/**
Bonus uppgift 3 på LTU, Oscar Dahlberg
*/

public class Helicopter {

    private String callSign = "";
    private long rtX = 0L, rtY = 0L;
    private double distanceFlown = 0.0d;

    private boolean lightOn = false;
    //Säger om ljuset är på, true, eller om ljuset är av, false

    public Helicopter(String name) {
        callSign = name;
    }

    public void flyTo(long x, long y) {

        double dx = Math.abs(x - rtX);
        double dy = Math.abs(y - rtY);

        distanceFlown +=
                Math.sqrt(dx * dx + dy * dy);

        rtX = x;
        rtY = y;
    }

    public String callSign() {
        return callSign;
    }

    public long getX() {
        return rtX;
    }

    public long getY() {
        return rtY;
    }

    public double distanceFlown() {
        return distanceFlown;
    }

    public void turnOn(){
        lightOn = true;
    }
    public void turnOff(){
        lightOn = false;
    }
    public boolean lightIsOn(){
        return lightOn;
    }
}