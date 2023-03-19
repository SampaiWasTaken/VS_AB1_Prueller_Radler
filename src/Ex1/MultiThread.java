package Ex1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class MultiThread
{
    static ArrayList<Long> divisors = new ArrayList<Long>();

    public static void main(String[] args) throws IOException, InterruptedException
    {
        long startTime = System.nanoTime();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a number of threads: ");
        int num_threads = Integer.parseInt(in.readLine());
        long number = Long.MAX_VALUE;
        WorkerThread[] workers = new WorkerThread[num_threads];

        for (int i = 0; i < num_threads; i++)
        {
            WorkerThread worker = new WorkerThread(number, i * (long) (Math.sqrt(number) / num_threads) + 1, (i + 1) * (long) (Math.sqrt(number) / num_threads));
            workers[i] = worker;
            worker.start();
        }

        for (WorkerThread w : workers)
            w.join();
        System.out.println(Arrays.deepToString(divisors.toArray()));
        System.out.println("Ran for: " + ((System.nanoTime() - startTime) * Math.pow(10, -9)) + " seconds");
    }

    public static void findDivisors(long l, long start, long end)
    {
        for (long i = start; i <= end; i++)
            if (l % i == 0)
                synchronized (divisors)
                {
                    divisors.add(i);
                }
    }

    public static class WorkerThread extends Thread
    {
        private long number;
        private long start;
        private long end;

        public WorkerThread(long number, long start, long end)
        {
            this.number = number;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run()
        {
            findDivisors(number, start, end);
        }
    }
}
