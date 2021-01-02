import java.util.ArrayList;
import java.util.HashMap;

public class Day15 {

    public static void main(String[] args) {
        var input = AoCManager.getInput(15);
        int turn = 1;
        var numbers = new HashMap<Integer, ArrayList<Integer>>();
        var split = input.get(0).split(",");
        for (int i = 0; i < split.length; i++) {
            var aL = new ArrayList<Integer>();
            aL.add(i + 1);
            numbers.put(Integer.parseInt(split[i]), aL);
        }
        int lastNumber = Integer.parseInt(split[split.length - 1]);
        for (int i = split.length; i < 30000000; i++) {
            if (numbers.get(lastNumber).size() == 1) {
                lastNumber = 0;
                numbers.get(0).add(i + 1);
            } else {
                var numberList = numbers.get(lastNumber);
                int size = numberList.size();
                if (size > 3) {
                    var temp = new ArrayList<Integer>();
                    temp.add(numberList.get(size - 2));
                    temp.add(numberList.get(size - 1));
                    numbers.put(lastNumber, temp);
                }
                int turnDifference = numberList.get(size - 1) - numberList.get(size - 2);
                lastNumber = turnDifference;
                numbers.putIfAbsent(lastNumber, new ArrayList<>());
                numbers.get(lastNumber).add(i + 1);
            }
        }
        System.out.println(lastNumber);
    }
}
