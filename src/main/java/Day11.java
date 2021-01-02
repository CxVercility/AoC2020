import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.stream.Collectors;

public class Day11 {

    static int width;
    static int height;
    static int amt = 4;

    public static void main(String[] args) {
        System.out.println("Part A :");
        extracted(false);
        System.out.println("Part B : ");
        extracted(true);
    }

    private static void extracted(boolean b) {
        if (b) {
            amt = 5;
        }
        var input = AoCManager.getInput(11);
        width = input.get(0).length() - 1;
        height = input.size() - 1;
        String prev = "";
        String crt = ".";
        Map<Integer, HashSet<Integer>> flip = new HashMap<>();
        while (!prev.equals(crt)) {
            prev = input.stream().map(Object::toString).collect(Collectors.joining(";"));
            for (int j = 0; j <= height; j++) {
                flip.putIfAbsent(j, new HashSet<>());
                for (int i = 0; i <= width; i++) {
                    if (input.get(j).charAt(i) == '.') {
                        continue;
                    }
                    int occ = 0;
                    if (b) {
                        occ = checkAdjacentLines(input, i, j);
                    } else {
                        occ = checkAdjacent(input, i, j);
                    }
                    if (input.get(j).charAt(i) == 'L') {
                        if (occ == 0) {
                            flip.get(j).add(i);
                        }
                    } else if (occ >= amt) {
                        flip.get(j).add(i);
                    }
                }
            }
            for (Integer i : flip.keySet()) {
                var temp = input.get(i).toCharArray();
                for (Integer j : flip.get(i)) {
                    char t = temp[j];
                    if (t == 'L') {
                        t = '#';
                    } else {
                        t = 'L';
                    }
                    temp[j] = t;
                }
                input.set(i, new String(temp));
            }
            crt = input.stream().map(Object::toString).collect(Collectors.joining(";"));

            flip.clear();
        }
        crt = crt.replaceAll("L|\\.|;", "");
        System.out.println(crt.length());
    }

    private static int checkAdjacent(java.util.List<String> input, int i, int j) {
        int occ = 0;
        for (int s = i - 1; s <= i + 1; s++) {
            if (s < 0 || s > width) {
                continue;
            }
            for (int t = j - 1; t <= j + 1; t++) {
                if (s == i && t == j) {
                    continue;
                }
                if (t < 0 || t > height) {
                    continue;
                }
                if (input.get(t).charAt(s) == '#') {
                    occ++;
                }
            }
        }
        return occ;
    }

    private static int checkAdjacentLines(java.util.List<String> input, int i, int j) {
        int occ = 0;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (x == 0 && y == 0) {
                    continue;
                }
                f:
                for (int disp = 1; ; disp++) {
                    int s = j + disp * y;
                    int t = i + disp * x;
                    if (s > height || s < 0 || t < 0 || t > width) {
                        break;
                    }
                    char temp = input.get(s).charAt(t);
                    switch (temp) {
                        case '#':
                            occ++;
                            break f;
                        case 'L':
                            break f;
                        case '.':
                    }
                }
            }
        }
        return occ;
    }


}
