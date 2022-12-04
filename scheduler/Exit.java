package scheduler;

import scheduler.Arrival;
import scheduler.Departure;
import scheduler.scheduling.policies.Policy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Exit extends Thread {
    private final Arrival arrival;
    private final Policy policy;
    private final Departure departure1;
    private final Departure departure2;
    private final BufferedReader input;
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_RESET = "\u001B[0m";

    public Exit(Arrival arrival, Departure departure1, Departure departure2, Policy policy) {
        this.arrival = arrival;
        this.departure1 = departure1;
        this.departure2 = departure2;
        this.policy = policy;
        input = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() {
        if (this.departure2 == null) {
            try {
                String quit = input.readLine();
                while (true) {
                    if (quit.equals("q")) {
                        this.arrival.exit();
                        this.departure1.exit();
                        break;
                    }
                }
            } catch(IOException io) {}
        } else  {
            try {
                String quit = input.readLine();
                while (true) {
                    if (quit.equals("q")) {
                        this.arrival.exit();
                        this.departure1.exit();
                        this.departure2.exit();
                        break;
                    }
                }
            } catch(IOException io) {
                io.printStackTrace();
            }
        }
        synchronized (this.policy) {
            System.out.println(ANSI_BLUE + "\nQuedaron " + this.policy.size() + " procesos en cola.\n" + ANSI_RESET);
        }
    }
}