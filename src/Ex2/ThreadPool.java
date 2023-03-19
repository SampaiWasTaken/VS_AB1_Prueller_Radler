package Ex2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool
{
    static ConcurrentLinkedQueue<Long> queue = new ConcurrentLinkedQueue<Long>();
    static long l = (long) Math.pow(2, 61) - 1;
    static boolean isPrime = true;

    public static void main(String[] args) throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter a number of threads: ");
        int num_threads = Integer.parseInt(in.readLine());
        long startTime = System.nanoTime();
        ExecutorService pool = Executors.newFixedThreadPool(num_threads);

        for (long i = 2; i <= Math.sqrt(l); i += 10000)
            queue.add(i);

        for (int i = 0; i < num_threads; i++)
        {
            WorkerThread worker = new WorkerThread();
            pool.execute(new WorkerThread());
        }

        pool.shutdown();

        System.out.println(l + " is " + (isPrime ? "prime" : "not prime"));
        System.out.println("Ran for: " + ((System.nanoTime() - startTime) * Math.pow(10, -9)) + " seconds");
    }

    public static class WorkerThread implements Runnable
    {
        @Override
        public void run()
        {
            while (!queue.isEmpty())
                for (long i = queue.poll(); i < i + 10; i++)
                    if (l % i == 0)
                    {
                        isPrime = false;
                        queue.clear();
                        break;
                    }
        }
    }
}
