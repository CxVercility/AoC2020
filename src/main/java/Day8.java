import java.util.HashSet;
import java.util.Set;

public class Day8 {

    public static void main(String[] args) {
        String[] input = AoCManager.getInput(8).toArray(new String[0]);
        final String[] inputCopy = input.clone();

        String[] split;
        loop:
        for (int i = 0; i < input.length; i++) {
            input = inputCopy.clone();
            split = input[i].split(" ");
            if (split[0].equals("jmp")) {
                input[i] = "nop " + split[1];
            } else if (split[0].equals("nop")) {
                input[i] = "jmp " + split[1];
            }
            int acc = 0;
            int iC = 0;
            Set<Integer> visited = new HashSet<>();
            while (!visited.contains(Integer.valueOf(iC))) {
                if (iC >= input.length) {
                    System.out.println(acc);
                    break loop;
                }
                visited.add(iC);
                split = input[iC].split(" ");
                switch (split[0]) {
                    case "nop":
                        iC++;
                        break;
                    case "acc":
                        acc += Integer.parseInt(split[1]);
                        iC++;
                        break;
                    case "jmp":
                        iC += Integer.parseInt(split[1]);
                        break;
                    default:
                        throw new IllegalArgumentException(split[0] + " " + split[1]);
                }
            }
        }
    }
}
