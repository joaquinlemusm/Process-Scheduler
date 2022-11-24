import scheduler.scheduling.policies.FCFS;
import scheduler.scheduling.policies.LCFS;
import scheduler.scheduling.policies.RR;
import scheduler.scheduling.policies.PP;
import scheduler.Arrival;
import scheduler.Departure;
import scheduler.Exit;

/** 
    @author Joaquín Lemus
*/

public class ProcessScheduler {
    public static void main(String[] args) {
        if (args[0].equals("-help")) {
            help();
        } else {
            try {
                String dual = null;
                int j = 0;
                if (args[0].equals("-dual")) {
                    j = 1;
                    dual = args[0];
                }
                String policy = args[0 + j];
                String inputTime = args[1 + j];
                String arthTime = args[2 + j];
                String ioTime = args[3 + j];
                String conditionalTime = args[4 + j];
                String loopTime = args[5 + j];
                String quantumTime = "";
                if (policy.equals("-rr")) {
                    quantumTime = args[6+j];
                }

                double arithmetic = Double.parseDouble(arthTime);
                double inputOutput = Double.parseDouble(ioTime);
                double conditional = Double.parseDouble(conditionalTime);
                double loop = Double.parseDouble(loopTime);

                int length = args.length;

                if (dual == null) {
                    simpleProcess(policy, inputTime, arithmetic, inputOutput, conditional, loop, quantumTime, length);
                } else {
                    dualProcess(policy, inputTime, arithmetic, inputOutput, conditional, loop, quantumTime, length);
                }
            } catch (Exception e) {
                System.out.println("No se ingresaron los parámetros necesarios.");
            }
        }
    }

    private static void simpleProcess(String policy, String inputTime, double arithmetic, double inputOutput, double conditional, double loop, String quantumTime, int length) {
        if (policy.equals("-fcfs")) {
            FCFS processFCFS = new FCFS();
            Arrival g1 = new Arrival(processFCFS, inputTime, arithmetic, inputOutput, conditional, loop);
            Departure g2 = new Departure(processFCFS,0, "CPU 1");
            Exit e1 = new Exit(g1, g2, null, processFCFS);
            System.out.println("=======================================");
            System.out.println("\tFirst-Come First-Served");
            System.out.println("=======================================\n");
            g1.start();
            g2.start();
            e1.start();
        } else if (policy.equals("-lcfs")) {
            LCFS processLCFS = new LCFS();
            Arrival g1 = new Arrival(processLCFS, inputTime, arithmetic, inputOutput, conditional, loop);
            Departure g2 = new Departure(processLCFS, 0, "CPU 1");
            Exit e1 = new Exit(g1, g2, null, processLCFS);
            System.out.println("=======================================");
            System.out.println("\tLast-Come First-Served");
            System.out.println("=======================================\n");
            g1.start();
            g2.start();
            e1.start();
        } else if (policy.equals("-rr") && length == 7) {
            double quantum = Double.parseDouble(quantumTime);
            RR processRR = new RR(quantum);
            Arrival g1 = new Arrival(processRR, inputTime, arithmetic, inputOutput, conditional, loop);
            Departure g2 = new Departure(processRR,quantum, "CPU 1");
            Exit e1 = new Exit(g1, g2, null, processRR);
            System.out.println("=======================================");
            System.out.println("\tRound - Robin");
            System.out.println("=======================================\n");
            g1.start();
            g2.start();
            e1.start();
        } else if (policy.equals("-pp")) {
            PP processPP = new PP();
            Arrival g1 = new Arrival(processPP, inputTime, arithmetic, inputOutput, conditional, loop);
            Departure g2 = new Departure(processPP, 0, "CPU 1");
            Exit e1 = new Exit(g1, g2, null, processPP);
            System.out.println("=======================================");
            System.out.println("\tPriority Policy");
            System.out.println("=======================================\n");
            g1.start();
            g2.start();
            e1.start(); 
        } else {
            System.out.println("ERROR! Bandera INCORRECTA.");
        }
    }

    private static void dualProcess(String policy, String inputTime, double arithmetic, double inputOutput, double conditional, double loop, String quantumTime, int length) {
        if (policy.equals("-fcfs")) {
            FCFS processFCFS = new FCFS();
            Arrival g1 = new Arrival(processFCFS, inputTime, arithmetic, inputOutput, conditional, loop);
            Departure g2 = new Departure(processFCFS, 0, "CPU 1");
            Departure g3 = new Departure(processFCFS, 0, "CPU 2");
            Exit e1 = new Exit(g1, g2, g3, processFCFS);
            System.out.println("=======================================");
            System.out.println("\tFirst-Come First-Served");
            System.out.println("=======================================\n");
            g1.start();
            g2.start();
            g3.start();
            e1.start();
        } else if (policy.equals("-lcfs")) {
            LCFS processLCFS = new LCFS();
            Arrival g1 = new Arrival(processLCFS, inputTime, arithmetic, inputOutput, conditional, loop);
            Departure g2 = new Departure(processLCFS, 0, "CPU 1");
            Departure g3 = new Departure(processLCFS, 0, "CPU 2");
            Exit e1 = new Exit(g1, g2, g3, processLCFS);
            System.out.println("=======================================");
            System.out.println("\tLast-Come First-Served");
            System.out.println("=======================================\n");
            g1.start();
            g2.start();
            g3.start();
            e1.start();
        } else if (policy.equals("-rr") && length == 8) {
            double quantum = Double.parseDouble(quantumTime);
            RR processRR = new RR(quantum);
            Arrival g1 = new Arrival(processRR, inputTime, arithmetic, inputOutput, conditional, loop);
            Departure g2 = new Departure(processRR,quantum, "CPU 1");
            Departure g3 = new Departure(processRR,quantum, "CPU 2");
            Exit e1 = new Exit(g1, g2, g3, processRR);
            System.out.println("=======================================");
            System.out.println("\tRound - Robin");
            System.out.println("=======================================\n");
            g1.start();
            g2.start();
            g3.start();
            e1.start();
        } else if (policy.equals("-pp")) {
            PP processPP = new PP();
            Arrival g1 = new Arrival(processPP, inputTime, arithmetic, inputOutput, conditional, loop);
            Departure g2 = new Departure(processPP, 0, "CPU 1");
            Departure g3 = new Departure(processPP, 0, "CPU 2");
            Exit e1 = new Exit(g1, g2, g3, processPP);
            System.out.println("=======================================");
            System.out.println("\tPriority Policy");
            System.out.println("=======================================\n");
            g1.start();
            g2.start();
            g3.start();
            e1.start(); 
        } else {
            System.out.println("ERROR! Bandera INCORRECTA.");
        }
    }

    private static void help() {
        System.out.println("\n\tGracias por usar Process Scheduler Hyperion version 3.0.\n\n");
        System.out.println("La disposicion para la utilizacion del servicio es la siguiente: \n");
        System.out.println("First-Come First-Served (FCFS)");
        System.out.println("\tjava ProcessScheduler -fcfs rango_tiempo_ingreso arith io cond loop  o");
        System.out.println("Last-Come First-Served (LCFS)");
        System.out.println("\tjava ProcessScheduler -lcfs rango_tiempo_ingreso arith io cond loop  o");
        System.out.println("Round-Robin (RR)");
        System.out.println("\tjava ProcessScheduler -rr   rango_tiempo_ingreso arith io cond loop  quantum  o");
        System.out.println("Last-Come First-Served (LCFS)");
        System.out.println("\tjava ProcessScheduler -pp   rango_tiempo_ingreso arith io cond loop\n\n");
        System.out.println("Para usar la funcion dual, ingrese la bandera \"-dual\" antes de la politica a implementar. Acantando la forma :");
        System.out.println("\tjava ProcessScheduler -dual  -politica  rango_tiempo  arith io cond loop\n\n");
        System.out.println("Gracias por su preferencia.\n");

    }
}