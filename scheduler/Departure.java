package scheduler;

import scheduler.scheduling.policies.Policy;
import scheduler.processing.ArithmeticProcess;
import scheduler.processing.ConditionalProcess;
import scheduler.processing.InputOutputProcess;
import scheduler.processing.LoopProcess;
import scheduler.processing.SimpleProcess;
import scheduler.scheduling.policies.RR;
import scheduler.scheduling.policies.PP;

public class Departure extends Thread {
    private Policy policy;
    private double quantum;
    private boolean run;
    private String processor;
    private static int completed = 0;
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public Departure(Policy policy, double quantum, String processor) {
        this.policy = policy;
        this.quantum = quantum;
        this.processor = processor;
        run = true;
    } 

    public void run() {
        while (run) {
            if (this.policy instanceof RR) {
                roundRobinProcess(this.policy, this.quantum);
            } else if (this.policy instanceof PP) {
                priorityProcess(this.policy);
            } else {
                int time = 0;
                SimpleProcess s; // A -> 2
                try {
                    if (this.policy.next() != null) {
                        synchronized (this.policy) {
                            s = this.policy.next();
                            System.out.println("El " + this.processor + " ha empezando a atender a " + s.toString());
                            this.policy.remove();
                        }
                        time = (int)(pickProcessTime(s)*1000);
                        System.out.println();
                        try {
                            Thread.sleep(time);
                        } catch (Exception e) { 
                        }
                        System.out.println("El " + this.processor + " ha terminando de atender a " + s);
                        ++completed;
                        System.out.println("\n" + ANSI_GREEN + "Se han atendido " + completed + " procesos en total." + ANSI_RESET);
                        System.out.println();
                    }
                } catch (NullPointerException npe) {
                    System.out.println(ANSI_YELLOW + "¡No hay procesos en espera! \n" + ANSI_RESET);
                }
            }
        }
    }

    private void roundRobinProcess(Policy policy, double quantum) {
        SimpleProcess s;
        int quantumSleep = (int)(quantum*1000);
        try {
            if (this.policy.next() != null) {
                synchronized (this.policy) {
                    s = this.policy.next();
                    System.out.println("El " + this.processor + " ha empezando a atender a " + s.toString());
                    this.policy.remove();
                }
                double quantumProcessTime = pickQuantumTime(s);
                System.out.println();
                try {
                    Thread.sleep(quantumSleep);
                } catch (Exception e) { 
                }
                double remainder = quantumProcessTime - quantum;
                if (remainder > 0) {
                    System.out.println(ANSI_RED + "El proceso " + s.toString() + " no logro ser atendido completamente por " + this.processor + ". Vuelve a la cola. \n" + ANSI_RESET);
                    newQuantumTime(s, remainder);
                    synchronized (this.policy) {
                        this.policy.add(s);
                    }
                    System.out.println("Procesos en espera: " + this.policy.toString());
                    System.out.println();
                } else {
                    System.out.println("El " + this.processor + " ha terminando de atender a " + s);
                    ++completed;
                    System.out.println("\n" + ANSI_GREEN + "Se han atendido " + completed + " procesos en total." + ANSI_RESET);
                    System.out.println();           
                }
            }
        } catch (NullPointerException npe) {
            System.out.println(ANSI_YELLOW + "¡No hay procesos en espera! \n" + ANSI_RESET);
        }
    }

    private void priorityProcess(Policy policy) {
        SimpleProcess s;
        try {
            if (this.policy.next() != null) {
                synchronized (this.policy) {
                    s = this.policy.next();
                    System.out.println("El " + this.processor + " ha empezando a atender a " + s.toString());
                    this.policy.remove();
                }
                int time = (int)(1000*pickProcessTime(s));
                System.out.println();
                try {
                    Thread.sleep(time);
                } catch (Exception e) { 
                }
                System.out.println("El " + this.processor + " ha terminando de atender a " + s);
                ++completed;
                System.out.println("\n" + ANSI_GREEN + "Se han atendido " + completed + " procesos en total." + ANSI_RESET);
                System.out.println();            
                //System.out.println(this.policy.toString());
                // System.out.println(cola4.toString());
            }
        } catch (NullPointerException npe) {
            System.out.println(ANSI_YELLOW + "¡No hay procesos en espera! \n" + ANSI_RESET);
        }

    }

    public void newQuantumTime(SimpleProcess s, double remainingTime) {
        double newQuantum = 0;
        if (s instanceof ArithmeticProcess) {
            ArithmeticProcess ap = (ArithmeticProcess)s;
            newQuantum = ap.setQuantumTime(remainingTime);
        } else if (s instanceof InputOutputProcess) {
            InputOutputProcess io = (InputOutputProcess)s;
            newQuantum = io.setQuantumTime(remainingTime);
        } else if (s instanceof ConditionalProcess) {
            ConditionalProcess cp = (ConditionalProcess)s;
            newQuantum = cp.setQuantumTime(remainingTime);
        } else if (s instanceof LoopProcess) {
            LoopProcess lp = (LoopProcess)s;
            newQuantum = lp.setQuantumTime(remainingTime);
        }
    }

    private double pickProcessTime(SimpleProcess s) {
        double time = 0;
        if (s instanceof ArithmeticProcess) {
            ArithmeticProcess ap = (ArithmeticProcess)s;
            time = ap.getTime();
        } else if (s instanceof InputOutputProcess) {
            InputOutputProcess io = (InputOutputProcess)s;
            time = io.getTime();
        } else if (s instanceof ConditionalProcess) {
            ConditionalProcess cp = (ConditionalProcess)s;
            time = cp.getTime();
        } else if (s instanceof LoopProcess) {
            LoopProcess lp = (LoopProcess)s;
            time = lp.getTime();
        }
        return time;
    }

    private String pickProcessType(SimpleProcess s) {
        String type = "";
        if (s instanceof ArithmeticProcess) {
            ArithmeticProcess ap = (ArithmeticProcess)s;
            type = ap.getType();
        } else if (s instanceof InputOutputProcess) {
            InputOutputProcess io = (InputOutputProcess)s;
            type = io.getType();
        } else if (s instanceof ConditionalProcess) {
            ConditionalProcess cp = (ConditionalProcess)s;
            type = cp.getType();
        } else if (s instanceof LoopProcess) {
            LoopProcess lp = (LoopProcess)s;
            type = lp.getType();
        }
        return type;
    }

    public double pickQuantumTime(SimpleProcess s) {
        double quantumTime = 0;
        if (s instanceof ArithmeticProcess) {
            ArithmeticProcess ap = (ArithmeticProcess)s;
            quantumTime = ap.getQuantumTime();
        } else if (s instanceof InputOutputProcess) {
            InputOutputProcess io = (InputOutputProcess)s;
            quantumTime = io.getQuantumTime();
        } else if (s instanceof ConditionalProcess) {
            ConditionalProcess cp = (ConditionalProcess)s;
            quantumTime = cp.getQuantumTime();
        } else if (s instanceof LoopProcess) {
            LoopProcess lp = (LoopProcess)s;
            quantumTime = lp.getQuantumTime();
        }
        return quantumTime;
    }

    public void exit() {
        run = false;
    }
}