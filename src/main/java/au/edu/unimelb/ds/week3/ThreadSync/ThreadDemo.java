package au.edu.unimelb.ds.week3.ThreadSync;

class ThreadDemo extends Thread {
    private String threadName;
    PrintDemo PD;

    ThreadDemo(String name, PrintDemo pd) {
        threadName = name;
        PD = pd;
    }

    public void run() {
        // Synchronize the threads!
        synchronized (PD) {
            PD.printCount(threadName);
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

}