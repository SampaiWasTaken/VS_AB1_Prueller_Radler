import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class SingleThread
{


    public static void main(String[] args) throws IOException
    {

        long startTime = System.nanoTime();
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
//        System.out.print("Enter a big number: ");
//        long number = Long.parseLong(in.readLine());
        long number = 10000000000L;

        System.out.println(Arrays.deepToString(findDivisors(number).toArray()));
        System.out.println("Ran for: " + ((System.nanoTime() - startTime) * Math.pow(10, -9)) + " seconds");
    }

    public static ArrayList<Long> findDivisors(long l)
    {
        ArrayList<Long> divisors = new ArrayList<Long>();
        for (long i = 1; i <= l; i++)
            if (l % i == 0)
                divisors.add(i);
        return divisors;
    }
}

