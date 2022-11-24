package scheduler.scheduling.policies;
import scheduler.processing.SimpleProcess;

import java.util.Stack;

/** 
    @author Joaquín Lemus
*/

public class LCFS extends Policy implements Enqueable   {
    private Stack<SimpleProcess> lcfs;

    public LCFS() {
        lcfs = new Stack<SimpleProcess>();
    }

    public Stack<SimpleProcess> process() {
        return lcfs;
    }

    @Override
    public void add(SimpleProcess p) {
        lcfs.push(p);
        this.size ++;
    }

    @Override
    public void remove() {
        if (!lcfs.isEmpty()) {
            lcfs.pop();
            this.size --;
        }
    }
    
    @Override
    public SimpleProcess next() {
        if (!lcfs.isEmpty()) {
            return lcfs.peek();
        } return null;
    }

    @Override
    public String toString() {
        return lcfs + "";
    }
}