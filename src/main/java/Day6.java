import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day6 {

    public static void main(String[] args) throws IOException {
        final List<String> input = AoCManager.getInput(6);
        String answers = "";
        int amount = 0;
        boolean first = true;
        for (String s : input) {
            if (s.isBlank()) {
                System.out.println(answers);
                amount += answers.chars().distinct().count();
                answers = "";
                first = true;
                continue;
            } else {
                if (first) {
                    answers = s;
                    first = false;
                } else {
                    Set<String> removable = new HashSet<>();
                    for (int i = 0; i < answers.length(); i++) {
                        if (!s.contains("" + answers.charAt(i))) {
                            removable.add("" + answers.charAt(i));
                        }
                    }
                    for (String ss : removable) {
                        answers = answers.replace(ss, "");
                    }
                }
                continue;
            }
        }
        System.out.println(amount);
    }
}


