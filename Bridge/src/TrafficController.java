
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrafficController {

    private int carsFromLeft = 0;
    private int carsFromRight = 0;

    public synchronized void enterLeft() {
        carsFromLeft++;
        System.out.println("enterLeft: ");
        System.out.println("    carsFromLeft = " + carsFromLeft);
        System.out.println("    carsFromRight = " + carsFromRight);
        while (carsFromRight >= 1 && !(carsFromLeft >= 2)) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(TrafficController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public synchronized void enterRight() {
        carsFromRight++;
        System.out.println("enterRight: ");
        System.out.println("    carsFromLeft = " + carsFromLeft);
        System.out.println("    carsFromRight = " + carsFromRight);
        while (carsFromLeft >= 1  && !(carsFromRight >= 2)) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(TrafficController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public synchronized void leaveLeft() {
        carsFromRight--;
        notifyAll();
    }

    public synchronized void leaveRight() {
        carsFromLeft--;
        notifyAll();
    }
}
