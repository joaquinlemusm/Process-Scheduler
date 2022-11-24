package scheduler;

import scheduler.processing.ArithmeticProcess;
import scheduler.processing.ConditionalProcess;
import scheduler.processing.InputOutputProcess;
import scheduler.processing.LoopProcess;
import scheduler.processing.SimpleProcess;

import java.util.Random;

public class RandomProcess {
    private double arithmeticTime;
    private double ioTime;
    private double loopTime;
    private double conditionalTime;
    private static int id = 0;

    public RandomProcess(double arithmeticTime, double ioTime, double conditionalTime, double loopTime) {
        this.arithmeticTime = arithmeticTime;
        this.ioTime = ioTime;
        this.conditionalTime = conditionalTime;
        this.loopTime = loopTime;
    }
    
    public SimpleProcess pickProcess() {
        Random random = new Random();
        SimpleProcess process = null;
        int max = 4;
        int min = 1;
        int opt = random.nextInt((max-min)+1)+min;

        switch(opt) {
            case 1:
                ArithmeticProcess arithmeticProcess = new ArithmeticProcess(++id, this.arithmeticTime);
                process = arithmeticProcess;
                break;
            case 2:
                InputOutputProcess ioProcess = new InputOutputProcess(++id, this.ioTime);
                process = ioProcess;
                break;
            case 3:
                LoopProcess loopProcess = new LoopProcess(++id, this.loopTime);
                process = loopProcess;
                break;
            case 4:
                ConditionalProcess conditionalProcess = new ConditionalProcess(++id, this.conditionalTime);
                process = conditionalProcess;
                break;
        }

        return process;
    }

    public double pickProcessTime(SimpleProcess s) {
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

    public String pickProcessType(SimpleProcess s) {
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
            quantumTime = this.arithmeticTime;
        } else if (s instanceof InputOutputProcess) {
            InputOutputProcess io = (InputOutputProcess)s;
            quantumTime = this.ioTime;
        } else if (s instanceof ConditionalProcess) {
            ConditionalProcess cp = (ConditionalProcess)s;
            quantumTime = this.conditionalTime;
        } else if (s instanceof LoopProcess) {
            LoopProcess lp = (LoopProcess)s;
            quantumTime = this.loopTime;
        }
        return quantumTime;
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
}