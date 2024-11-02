package lp2.resources;

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
        return Response.ok(primes).build();
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
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
    public Response sort(Map<String, Object> request) {
        Object numbersObj = request.get("numbers");
        if (numbersObj instanceof List<?>) {
            List<?> numbersList = (List<?>) numbersObj;
            int[] numbers = numbersList.stream().map(Number.class::cast).mapToInt(Number::intValue).toArray();
            bubbleSort(numbers);
            return Response.ok(numbers).build();
        }

        return Response.status(404).build();
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

    @POST
    @Path("/sum-array")
    @Produces(MediaType.APPLICATION_JSON)
    public Response sumArray(Map<String, Object> request) {
        Object numbersObj = request.get("numbers");
        if (numbersObj instanceof List<?>) {
            List<?> numbersList = (List<?>) numbersObj;
            int[] numbers = numbersList.stream().map(Number.class::cast).mapToInt(Number::intValue).toArray();
            int sum = sumArray(numbers);
            return Response.ok("Suma de todos los elementos: " + sum).build();
        }

        return Response.status(404).build();
    }

    public int sumArray(int[] array) {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        return sum;
    }

    @POST
    @Path("/mcd")
    @Produces(MediaType.APPLICATION_JSON)
    public Response mcd(Map<String, Object> request) {
        Object number1Obj = request.get("number1");
        Object number2Obj = request.get("number2");

        int number1;
        int number2;

        number1 = Integer.parseInt(number1Obj.toString());
        number2 = Integer.parseInt(number2Obj.toString());

        int mcd = mcd(number1, number2);

        return Response.ok("Resultado: " + mcd).build();
    }

    public int mcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    @GET
    @Path("/armstrong/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response armstrong(@PathParam("number") int number) {
        boolean isArmstrong = isArmstrongNumber(number);
        return Response.ok("Es numero armstrong: " + isArmstrong).build();
    }

    public static boolean isArmstrongNumber(int number) {
        String numStr = Integer.toString(number);
        int numDigits = numStr.length();
        int sum = 0;

        for (char digitChar : numStr.toCharArray()) {
            int digit = Character.getNumericValue(digitChar);
            sum += Math.pow(digit, numDigits);
        }

        return sum == number;
    }

    @GET
    @Path("/convert-to-binary/{number}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response convertToBinary(@PathParam("number") int number) {
        String binary = Integer.toBinaryString(number);
        return Response.ok("Binario: " + binary).build();
    }
}
