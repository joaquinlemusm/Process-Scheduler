package scheduler.processing;

/** 
    @author Joaquín Lemus
*/

public class LoopProcess extends SimpleProcess {
    private double time;
    private String process;
    private double quantumTime;

    public LoopProcess(int id, double time) {
        super(id);
        this.time = time;
        this.process = "L";
        this.quantumTime = this.time;

    }

    public double getTime() {
        return this.time;
    }
    
    public double getQuantumTime() {
        return this.quantumTime;
    }
    
    public double setQuantumTime(double newTime) {
        this.quantumTime = newTime;
        return this.quantumTime;
    }

    public String getType() {
        return this.process;
    }

    @Override
    public String toString() {
        return "[id:"+ this.id+" time:"+ this.time+" quantumTime:"+this.quantumTime+" process:"+ this.process+"]";
    }
}