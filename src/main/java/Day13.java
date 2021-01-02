import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day13 {

    public static void main(String[] args) {
        var input = AoCManager.getInput(13);
        var split = input.get(1).split(",");
        int minimum = Integer.MAX_VALUE;
        int ID = -1;
        int timeStamp = Integer.parseInt(input.get(0));
        List<Integer> t1 = new ArrayList<>();
        List<Integer> t2 = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {

            if (split[i].equals("x")) {
                continue;
            }
            t1.add(Integer.parseInt(split[i]));
            t2.add(Integer.parseInt(split[i]) - i);
            int temp = -(timeStamp % Integer.parseInt(split[i])) + Integer.parseInt(split[i]);
            if (temp < minimum) {
                minimum = temp;
                ID = Integer.parseInt(split[i]);
            }
        }
        System.out.println("Part A : " + ID * minimum);
        System.out.println("Part B : " + chineseReminder(t1, t2));
    }

    private static long chineseReminder(List<Integer> reminders, List<Integer> moduli) {
        long product = reminders.stream()
                .mapToLong(i -> i)
                .reduce(1, (a, b) -> a * b);
        long sum = 0;

        for (int i = 0; i < reminders.size(); i++) {
            long partialProduct = product / reminders.get(i);
            long inverse = BigInteger.valueOf(partialProduct)
                    .modInverse(BigInteger.valueOf(reminders.get(i)))
                    .longValue();
            sum += partialProduct * inverse * moduli.get(i);
        }

        return sum % product;
    }
}
