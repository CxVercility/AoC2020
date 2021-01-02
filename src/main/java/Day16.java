import java.util.*;

public class Day16 {

    public static void main(String[] args) {
        var input = AoCManager.getInput(16);
        Set<Integer> invalidNumbers = new HashSet<>();
        for (int i = 0; i < 1000; i++) {
            invalidNumbers.add(i);
        }
        String[] split;
        int lower;
        int higher;
        boolean skip = false;
        List<Integer> result = new ArrayList<>();
        List<String> discardable = new ArrayList<>();
        String s;
        List<List<Integer>> numbersOnIndex = new ArrayList<>();
        String myticket;
        for (int i = 0; i < 20; i++) {
            numbersOnIndex.add(i, new ArrayList<>());
        }
        int index = 1; //where nearby tickets start
        for (String ss : input) {
            if (!ss.contains("nearby")) {
                index++;
            } else {
                break;
            }
        }
        for (String ss : input) {
            if (ss.isBlank()) {
                break;
            }

            split = ss.replaceAll("\\s", "").split("or(?=[0-9])|:");

            for (int i = 1; i < 3; i++) {
                lower = Integer.parseInt(split[i].substring(0, split[i].indexOf('-')));
                higher = Integer.parseInt(split[i].substring(split[i].indexOf('-') + 1));
                Set<Integer> removable = new HashSet<>();
                for (Integer j : invalidNumbers) {
                    if (lower <= j && j <= higher) {
                        removable.add(j);
                    }
                }
                invalidNumbers.removeAll(removable);
            }
        }
        for (int i = index; i < input.size(); i++) {
            s = input.get(i);
            var numbers = s.split(",");
            for (String n : numbers) {
                int j = Integer.parseInt(n);
                if (invalidNumbers.contains(j)) {
                    result.add(j);
                }
            }
        }
        myticket = input.get(index - 3);
        var numbers = myticket.split(",");
        for (int j = 0; j < numbers.length; j++) {
            numbersOnIndex.get(j).add(Integer.parseInt(numbers[j]));
        }
        int res = 0;
        for (Integer i : result) {
            res += i;
        }
        System.out.println("Part A : " + res);
        outer:
        for (int i = index; i < input.size(); i++) {
            s = input.get(i);
            split = s.split(",");
            for (String ss : split) {
                if (invalidNumbers.contains(Integer.parseInt(ss))) {
                    discardable.add(s);
                    continue outer;
                }
            }
        }
        input.removeAll(discardable);
        for (int i = index; i < input.size(); i++) {
            s = input.get(i);
            numbers = s.split(",");
            for (int j = 0; j < numbers.length; j++) {
                numbersOnIndex.get(j).add(Integer.parseInt(numbers[j]));
            }
        }
        Map<String, Tuple> fields = new HashMap<>();
        for (String ss : input) {
            if (ss.isBlank()) {
                break;
            }
            split = ss.split(": | or ");
            var tuple = new Tuple();
            tuple.lower = Integer.parseInt(split[1].substring(0, split[1].indexOf("-")));
            tuple.higher = Integer.parseInt(split[1].substring(1 + split[1].indexOf("-")));
            tuple.lower2 = Integer.parseInt(split[2].substring(0, split[2].indexOf("-")));
            tuple.higher2 = Integer.parseInt(split[2].substring(1 + split[2].indexOf("-")));
            fields.put(split[0], tuple);
        }
        Map<String, Integer> mapping = new HashMap<>();
        while (!fields.isEmpty()) {
            for (int i = 0; i < 20; i++) {
                List<String> possible = new ArrayList<>();
                out:
                for (String ss : fields.keySet()) {
                    var temp = fields.get(ss);
                    for (Integer j : numbersOnIndex.get(i)) {
                        if (!((temp.lower <= j && j <= temp.higher) || (temp.lower2 <= j && j <= temp.higher2))) {
                            continue out;
                        }
                    }
                    possible.add(ss);
                }
                if (possible.size() == 1) {
                    mapping.put(possible.get(0), i);
                    fields.remove(possible.get(0));
                }
            }
        }
        long resultB = 1;
        split = myticket.split(",");
        for (String ss : mapping.keySet()) {
            if (ss.startsWith("departure")) {
                resultB *= Integer.parseInt(split[mapping.get(ss)]);
            }
        }
        System.out.println(resultB);
    }
}

class Tuple {

    int lower;
    int higher;
    int lower2;
    int higher2;
}
