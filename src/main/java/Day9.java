public class Day9 {

    public static void main(String[] args) {
        var input = AoCManager.getInput(9);
        int lower = 0;
        int higher = 25;
        loop:
        while (higher < input.size()) {
            int current = Integer.parseInt(input.get(higher));
            for (int i = lower; i < higher; i++) {
                for (int j = i; j < higher; j++) {
                    if (Integer.parseInt(input.get(i)) + Integer.parseInt(input.get(j)) == current) {
                        lower++;
                        higher++;
                        continue loop;
                    }

                }
            }
            break;
        }
        int partA = Integer.parseInt(input.get(higher));
        System.out.println("Day 9 Part A : " + partA);
        int sum = 0;
        outer:
        for (int i = 0; i < input.size(); i++) {
            lower = higher = Integer.parseInt(input.get(i));
            sum = 0;
            for (int j = i; j < input.size(); j++) {
                int pJ = Integer.parseInt(input.get(j));
                if (pJ < lower) {
                    lower = pJ;
                } else if (pJ > higher) {
                    higher = pJ;
                }
                sum += pJ;
                if (sum > partA) {
                    continue outer;
                } else if (sum == partA) {
                    System.out.println("\nDay 9 Part B : " + (higher + lower));
                    break outer;
                }
            }
        }
    }
}
