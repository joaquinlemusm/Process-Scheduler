package scheduler.processing;

/** 
    @author Joaquín Lemus
*/

public class ConditionalProcess extends SimpleProcess {
    private double time;
    private String process;
    private double quantumTime;

    public ConditionalProcess(int id, double time) {
        super(id);
        this.time = time;
        this.process = "C";
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