import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day10 {

    static Integer[] jolts = new Integer[]{0, 0, 1};

    public static void main(String[] args) {
        var temp = AoCManager.getInput(10);
        var input = temp.stream().map(Integer::parseInt).collect(Collectors.toList());
        input.sort(Integer::compare);
        int prev = 0;
        for (Integer jolt : input) {
            jolts[jolt - prev - 1]++;
            prev = jolt;
        }
        System.out.println("Part A : " + jolts[0] * jolts[2]);
        input.add(0, 0);
        input.add(input.get(input.size() - 1) + 3);
        System.out.println("Part B : " + partB(input));
    }

    static Map<String, Long> cache = new HashMap<>();

    public static long partB(List<Integer> input) {
        String key = input.stream().map(Object::toString).collect(Collectors.joining(", "));
        if (cache.containsKey(key)) {
            return cache.get(key);
        }
        long amount = 1;
        for (int i = 1; i < input.size() - 1; i++) {
            if (input.get(i + 1) - input.get(i - 1) <= 3) {
                var newList = new ArrayList<Integer>(input.get(i - 1));
                newList.addAll(input.subList(i + 1, input.size() - 1));
                amount += partB(newList);
            }
        }
        cache.put(key, amount);
        return amount;
    }
}
