import java.io.IOException;
import java.util.*;

class Bag {

    public String name;
    public final Map<Bag, Integer> bags = new HashMap<>();

    public Bag(String name) {
        this.name = name;
    }
}

public class Day7 {

    public static void main(String[] args) throws IOException {
        final List<String> input = AoCManager.getInput(7);
        Set<String> bags = new HashSet<>();
        String[] split;
        boolean changed = true;
        List<String> removable = new ArrayList<>();
        String temp;
        Bag sG = new Bag("shiny gold");
        for (String s : input) {
            temp = s.replaceAll(",|\\.|(contain|bag)(s)?", "");
            temp = temp.replaceAll("\\s+", " ");
            split = temp.split(" ");
            if (s.startsWith("shiny gold")) {
                for (int i = 2; i < split.length; i = i + 3) {
                    Bag newBag = new Bag(split[i + 1] + " " + split[i + 2]);
                    sG.bags.put(newBag, Integer.valueOf(split[i]));
                    traverseBag(newBag, input);
                }
                break;
            }
        }
        System.out.println(countBags(sG));
    }

    public static int countBags(Bag bag) {
        int amount = 0;
        for (Bag bg : bag.bags.keySet()) {
            if (!bg.bags.isEmpty()) {
                amount += countBags(bg) * bag.bags.get(bg);
            }
            amount += bag.bags.get(bg);
        }
        return amount;
    }

    public static void traverseBag(Bag bag, List<String> input) {
        String temp;
        String[] split;
        for (String s : input) {
            if (!s.startsWith(bag.name)) {
                continue;
            }
            if (s.contains("no other")) {
                break;
            }
            temp = s.replaceAll(",|\\.|(contain|bag)(s)?", "");
            temp = temp.replaceAll("\\s+", " ");
            split = temp.split(" ");
            for (int i = 2; i < split.length; i = i + 3) {
                Bag newBag = new Bag(split[i + 1] + " " + split[i + 2]);
                bag.bags.put(newBag, Integer.valueOf(split[i]));
                traverseBag(newBag, input);
            }
            break;
        }
    }

    private static void partA(List<String> input, Set<String> bags, boolean changed, List<String> removable) {
        String temp;
        String[] split;
        while (changed) {
            changed = false;
            for (String s : input) {
                if (s.contains("shiny gold") && !s.startsWith("shiny gold")) {
                    changed = true;
                    split = s.split(" ");
                    bags.add(split[0] + " " + split[1]);
                    removable.add(s);
                    continue;
                } else if (s.contains("no other")) {
                    removable.add(s);
                    continue;
                } else {
                    temp = s.replaceAll("[0-9]|,|\\.|(contain|bag)(s)?", "");
                    temp = temp.replaceAll("\\s+", " ");
                    split = temp.split(" ");
                    for (int i = 2; i < split.length; i++) {
                        if (bags.contains(split[i++] + " " + split[i])) {
                            bags.add(split[0] + " " + split[1]);
                            removable.add(s);
                            changed = true;
                            break;
                        }
                    }
                }
            }
            input.removeAll(removable);
        }
    }
}
