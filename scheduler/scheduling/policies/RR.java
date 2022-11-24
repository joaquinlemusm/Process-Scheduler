package scheduler.scheduling.policies;
import scheduler.processing.SimpleProcess;

import java.util.concurrent.ConcurrentLinkedQueue;

/** 
    @author Joaquín Lemus
*/

public class RR extends Policy implements Enqueable {
    private ConcurrentLinkedQueue<SimpleProcess> rr;
    private double quantum;

    public RR(double quantum) {
        rr = new ConcurrentLinkedQueue<SimpleProcess>();
        this.quantum = quantum;
    }

    public ConcurrentLinkedQueue<SimpleProcess> process() {
        return rr;
    }

    @Override
    public void add(SimpleProcess p) {
        rr.add(p);
        this.size ++;
    }

    @Override
    public void remove() {
        rr.poll();
        this.size--;
    }
    
    @Override
    public SimpleProcess next() {
        return rr.peek();
    }

    @Override
    public String toString() {
        return rr + "";
    }
}