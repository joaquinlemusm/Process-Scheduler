package scheduler;

import scheduler.RandomProcess;
import scheduler.processing.SimpleProcess;
import scheduler.scheduling.policies.Policy;
import scheduler.scheduling.policies.PP;

import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Arrival extends Thread {
    private final Policy policy;
    private static int id = 0;
    private final String inputTime;
    private final double arithmeticTime;
    private final double ioTime;
    private final double loopTime;
    private final double conditionalTime;
    public boolean run;

    public Arrival(Policy policy, String inputTime, double arithmeticTime, double ioTime, double loopTime, double conditionalTime) {
        this.policy = policy;
        this.inputTime = inputTime;
        this.arithmeticTime = arithmeticTime;
        this.ioTime = ioTime;
        this.loopTime = loopTime;
        this.conditionalTime = conditionalTime;
        run = true;
    }

    public void run() {
        while (run) {
            RandomProcess process = new RandomProcess(this.arithmeticTime, this.ioTime, this.loopTime, this.conditionalTime);
            ++id;
            SimpleProcess currentProcess = process.pickProcess();
            System.out.println("Process [id:"+id+"] [time:"+process.pickProcessTime(currentProcess)+"] [quantumTime:"+process.pickQuantumTime(currentProcess)+"] [type:"+process.pickProcessType(currentProcess)+"] has been added"); 
            if (this.policy instanceof PP) {
                ConcurrentLinkedQueue<SimpleProcess> processes = new ConcurrentLinkedQueue<SimpleProcess>();
                processes.add(currentProcess);
                SimpleProcess next = processes.peek();
                this.policy.add(next);
                processes.remove();
                synchronized (this.policy) {
                    System.out.println("Procesos en espera: ");
                    System.out.println(this.policy.toString());
                    System.out.println();
                }
            } else {
                this.policy.add(currentProcess);
                synchronized (this.policy) {
                    System.out.println("Procesos en espera: " + this.policy.toString());
                    System.out.println();
                }
            }
            try {
                Thread.sleep(setInputTime(this.inputTime));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int setInputTime(String time) {
        Random random = new Random();
        String[] input = time.split("-");
        double startD = Double.parseDouble(input[0]);
        double endD = Double.parseDouble(input[1]);
        int start = (int)(startD *1000);
        int end = (int)(endD *1000);
        return random.nextInt((end - start) + 1) + start;
    }

    public void exit() {
        run = false;
    }
}