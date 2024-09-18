package lp2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/algorithms")
public class ParcialUnoResource {

    @GET
    @Path("/prime-numbers/{n}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrimeNumbers(@PathParam("n") int n) {
        List<Integer> primes = new ArrayList<>();
        int count = 0;
        int number = 2;
        while (count < n) {
            if (isPrime(number)) {
                primes.add(number);
                count++;
            }
            number++;
        }
        return Response.ok( primes).build();
    }

    private boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    @GET
    @Path("/sum-digits/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sumDigits(@PathParam("number") int number) {
        int sum = 0;
        int num = Math.abs(number);
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return Response.ok("Suma: " + sum).build();
    }

    @GET
    @Path("/fibonacci/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response fibonacci(@PathParam("number") int number) {
        List<Integer> secuence = new ArrayList<>();
        int a = 0, b = 1;

        for (int i = 0; i < number; i++) {
            secuence.add(a);
            int next = a + b;
            a = b;
            b = next;
        }
        return Response.ok(secuence).build();
    }

    @POST
    @Path("/sort")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sort(int[] numbers) {
        bubbleSort(numbers);
        return Response.ok(numbers).build();
    }

    private void bubbleSort(int[] array) {
        int n = array.length;
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            
            if (!swapped) {
                break;
            }
        }
    }

    @GET
    @Path("/palindrome/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response palindrome(@PathParam("number") String number) {
        boolean isPalindrome = isPalindrome(number);
        return Response.ok("Es palindromo: " + isPalindrome).build();
    }

    private boolean isPalindrome(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    @POST
    @Path("/power")
    @Produces(MediaType.APPLICATION_JSON)
    public Response power(Map<String, Object> request) {
        Object baseObj = request.get("base");
        Object exponentObj = request.get("exponente");

        double base;
        int exponent;

        base = Double.parseDouble(baseObj.toString());
        exponent = Integer.parseInt(exponentObj.toString());

        double result = Math.pow(base, exponent);

        return Response.ok("Resultado: " + result).build();
    }

    @GET
    @Path("/perfect-number/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response perfectNumber(@PathParam("number") int number) {
        boolean isPerfect = isPerfectNumber(number);
        return Response.ok("Es numero perfecto: " + isPerfect).build();
    }

    public static boolean isPerfectNumber(int number) {
        if (number <= 1) {
            return false; // 
        }

        int sum = 0;
        
        for (int i = 1; i <= number / 2; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }

        return sum == number;
    }

    @GET
    @Path("/factorial/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response factorial(@PathParam("number") int number) {
        long factorial = calculateFactorial(number);
        return Response.ok("Factorial: " + factorial).build();
    }

    public static long calculateFactorial(int number) {
        long result = 1;
        for (int i = 1; i <= number; i++) {
            result *= i;
        }

        return result;
    }

}