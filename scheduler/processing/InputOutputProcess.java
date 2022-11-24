package scheduler.processing;

/** 
    @author Joaquín Lemus
*/

public class InputOutputProcess extends SimpleProcess {
    private double time;
    private String process;
    private double quantumTime;

    public InputOutputProcess(int id, double time) {
        super(id);
        this.time = time;
        this.process = "IO";
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