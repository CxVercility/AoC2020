import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day5 {

    public static void main(String[] args) throws IOException {
        final List<String> input = AoCManager.getInput(5);
        final Set<Integer> seats = new HashSet<>();
        input.add(0, "FBFBBFFRLR");
        final char front = 'F';
        final char back = 'B';
        final char left = 'L';
        final char right = 'R';
        final int maxRow = 127;
        final int maxColumn = 7;
        int z = 7 / 2;
        int max = 0;
        for (String key : input) {
            int lowerC = 0;
            int upperC = maxColumn;
            int lowerR = 0;
            int upperR = maxRow;
            for (int i = 0; i < 6; i++) {
                if (key.charAt(i) == front) {
                    upperR = (lowerR + upperR) / 2;
                } else if (key.charAt(i) == back) {
                    lowerR = (lowerR + upperR);
                    if (lowerR % 2 == 1) {
                        lowerR++;
                    }
                    lowerR /= 2;
                } else {
                    throw new IllegalArgumentException();
                }
            }
            if (key.charAt(6) == front) {
                upperR = 0;
            } else {
                lowerR = 0;
            }
            for (int i = 7; i < 9; i++) {
                if (key.charAt(i) == left) {
                    upperC = (lowerC + upperC) / 2;
                } else if (key.charAt(i) == right) {
                    lowerC = (lowerC + upperC);
                    if (lowerC % 2 == 1) {
                        lowerC++;
                    }
                    lowerC /= 2;
                } else {
                    throw new IllegalArgumentException();
                }
            }
            if (key.charAt(9) == left) {
                upperC = 0;
            } else {
                lowerC = 0;
            }
            int res = (upperC + lowerC) + 8 * (upperR + lowerR);
            seats.add(res);
            max = res > max ? res : max;
        }
        System.out.println(max + "\n");
        for (int i = 0; i < 8 * 127 + 7; i++) {
            if (!seats.contains(i)) {
                if (seats.contains(i - 1) && seats.contains(i + 1)) {
                    System.out.println("Seat : " + i);
                }
            }
        }
    }
}
