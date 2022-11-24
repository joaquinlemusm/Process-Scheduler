package scheduler.scheduling.policies;

import scheduler.processing.SimpleProcess;
import scheduler.processing.ArithmeticProcess;
import scheduler.processing.ConditionalProcess;
import scheduler.processing.InputOutputProcess;
import scheduler.processing.LoopProcess;
import scheduler.processing.SimpleProcess;
 
import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;

/** 
    @author Joaquín Lemus
*/

public class PP extends Policy implements Enqueable{
    private LinkedList<ConcurrentLinkedQueue<SimpleProcess>> pp;
    public ConcurrentLinkedQueue<SimpleProcess> priority1;
    public ConcurrentLinkedQueue<SimpleProcess> priority2;
    public ConcurrentLinkedQueue<SimpleProcess> priority3;
    public ConcurrentLinkedQueue<SimpleProcess> priority4;


    public PP() {
        pp = new LinkedList<ConcurrentLinkedQueue<SimpleProcess>>();
        for (int i=0; i < 4; i++) {
			pp.add(i,new ConcurrentLinkedQueue<SimpleProcess>());
		}
        priority1 = pp.get(0); // input/output
        priority2 = pp.get(1); // aritméticos
        priority3 = pp.get(2); // condicionales 
        priority4 = pp.get(3); // iterativos
    }

    @Override
    public void add(SimpleProcess p) {
        if (p instanceof InputOutputProcess) {
            priority1.add(p);
        } else if (p instanceof ArithmeticProcess) {
            priority2.add(p);
        } else if (p instanceof ConditionalProcess) {
            priority3.add(p);
        } else if (p instanceof LoopProcess) {
            priority4.add(p);
        }
        this.size ++;
    }

    @Override
    public void remove() {
        if (priority1.size() != 0) {
            priority1.poll();
        } else if (priority2.size() != 0) {
            priority2.poll();
        } else if (priority3.size() != 0) {
            priority3.poll();
        } else if (priority4.size() != 0) {
            priority4.poll();
        }
        this.size--;
    }
    
    @Override
    public SimpleProcess next() {
        SimpleProcess sp = null;
        if (!priority1.isEmpty()) {
            sp = priority1.peek();
        } else if (!priority2.isEmpty()) {
            sp = priority2.peek();
        } else if (!priority3.isEmpty()) {
            sp = priority3.peek();
        } else if (!priority4.isEmpty()) {
            sp = priority4.peek();
        }
        return sp;
    }

    @Override
    public String toString() {
        return ("Input/Output: " + priority1 + "\n\n" +
                    "Arithmetic: " + priority2 + "\n\n" +
                    "Conditional: " + priority3 + "\n\n" +
                    "Loop: " + priority4 + "\n");
    }    
}