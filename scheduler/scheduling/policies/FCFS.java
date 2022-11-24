package scheduler.scheduling.policies;
import scheduler.processing.SimpleProcess;

import java.util.concurrent.ConcurrentLinkedQueue;

/** 
    @author Joaquín Lemus
*/

public class FCFS extends Policy implements Enqueable {
    private ConcurrentLinkedQueue<SimpleProcess> fcfs;

    public FCFS() {
        fcfs = new ConcurrentLinkedQueue<SimpleProcess>();
    }

    public ConcurrentLinkedQueue<SimpleProcess> process() {
        return fcfs;
    }

    @Override
    public void add(SimpleProcess p) {
        fcfs.add(p);
        this.size ++;
    }

    @Override
    public void remove() {
        fcfs.poll();
        this.size--;
    }
    
    @Override
    public SimpleProcess next() {
        return fcfs.peek();
    }

    @Override
    public String toString() {
        return fcfs + "";
    }
}