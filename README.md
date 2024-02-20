# Process Scheduler
The ProcessScheduler program is designed to simulate process scheduling using various scheduling policies. It provides implementations for First-Come First-Served (FCFS), Last-Come First-Served (LCFS), Round-Robin (RR), and Priority Policy (PP).

## Usage
To use the ProcessScheduler program, follow these instructions:

1. Clone the repository to your local machine:

```
git clone https://github.com/yourusername/ProcessScheduler.git
```
2. Compile the program using Java:
```
javac ProcessScheduler.java
```
3. Run the program using the following command:
```
java ProcessScheduler <flag> <parameters>
```
- <flag>: Specify the scheduling policy you want to use. Available flags are:
  -fcfs: First-Come First-Served.
  -lcfs: Last-Come First-Served.
  -rr: Round-Robin.
  -pp: Priority Policy.
  -help: Display usage instructions.
- <parameters>: Provide the necessary parameters for the selected policy.

## Examples
Here are examples of how to use the program with different scheduling policies:

  - First-Come First-Served (FCFS):
```
java ProcessScheduler -fcfs <parameters>
```
  - Last-Come First-Served (LCFS):
```
java ProcessScheduler -lcfs <parameters>
```
  - Round-Robin (RR):
```
java ProcessScheduler -rr <parameters>
```
  - Priority Policy (PP):
```
java ProcessScheduler -pp <parameters>
```
  - Help:
```
java ProcessScheduler -help
````
## Parameters
The parameters required for each scheduling policy include:

- rango_tiempo_ingreso: Range of time for arrival.
- arith: Arithmetic time.
- io: Input/output time.
- cond: Conditional time.
- loop: Loop time.
- quantum: Quantum time (required for Round-Robin only).
## Dual Mode
To use the dual mode, append the -dual flag before specifying the scheduling policy. Here's the format:
```
java ProcessScheduler -dual -policy <parameters>
```
## Testing
The program does not currently include a testing suite. However, you can manually test the functionality by running the program with different parameters and observing the scheduling behavior.

## Author
Joaquín Lemus
