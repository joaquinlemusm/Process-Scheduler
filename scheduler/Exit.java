package scheduler;

import scheduler.Arrival;
import scheduler.Departure;
import scheduler.scheduling.policies.Policy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Exit extends Thread {
    private Arrival arrival;
    private Policy policy;
    private Departure departure1;
    private Departure departure2;
    private BufferedReader input;
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
            } catch(IOException io) {}
        }
        synchronized (this.policy) {
            System.out.println(ANSI_BLUE + "\nQuedaron " + this.policy.size() + " procesos en cola.\n" + ANSI_RESET);
        }
    }
}