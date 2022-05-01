package au.edu.unimelb.ds.week4.ThreadSync;

class ThreadDemo extends Thread {
    private String threadName;
    PrintDemo PD;

    ThreadDemo(String name, PrintDemo pd) {
        threadName = name;
        PD = pd;
    }

    public void run() {
        // Synchronize the threads! Comment out to demo a racing condition.
        synchronized (PD) {
            PD.printCount(threadName);
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

}