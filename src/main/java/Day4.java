import java.io.IOException;
import java.util.List;
import java.util.function.Predicate;


enum Field {
    byr(s -> {
        try {
            return Integer.parseInt(s) >= 1920 ? (Integer.parseInt(s) <= 2020 ? true : false) : false;
        } catch (NumberFormatException e) {
            return false;
        }
    }),
    iyr(s -> {
        try {
            return Integer.parseInt(s) >= 2010 ? (Integer.parseInt(s) <= 2020 ? true : false) : false;
        } catch (NumberFormatException e) {
            return false;
        }
    }),
    eyr(s -> {
        try {
            return Integer.parseInt(s) >= 2020 ? (Integer.parseInt(s) <= 2030 ? true : false) : false;
        } catch (NumberFormatException e) {
            return false;
        }
    }),
    hgt(s -> {
        try {
            int hgt;
            if (s.contains("cm")) {
                hgt = Integer.parseInt(s.substring(0, 3));
                return hgt >= 150 ? (hgt <= 193 ? true : false) : false;
            } else if (s.contains("in")) {
                hgt = Integer.parseInt(s.substring(0, 2));
                return hgt >= 59 ? (hgt <= 76 ? true : false) : false;
            } else {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
    }),
    hcl(s -> {
        if (s.indexOf("#") != 0) {
            return false;
        }
        s = s.substring(1);

        return s.matches("[a-f0-9]{6}");
    }),

    ecl(s -> {
        String[] eyeColors = new String[]{"amb", "blu", "brn", "gry", "grn", "hzl", "oth"};
        for (String clr : eyeColors) {
            if (s.equals(clr)) {
                return true;
            }
        }
        return false;
    }),
    pid(s -> {

        return s.matches("[0-9]{9}");
    }),
    cid(s -> true);

    private Predicate<String> p;

    Field(Predicate<String> p) {
        this.p = p;
    }

    public boolean isValid(String s) {
        return p.test(s);
    }
}

public class Day4 {

    public static void main(String[] args) throws IOException {
        final List<String> input = AoCManager.getInput(4);
        final String[] requiredFields = new String[]{"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
        int amountA = 0;
        int amountB = 0;
        String temp = "";
        forlabel:
        for (String s : input) {
            if (!s.isBlank()) {
                temp += s + " ";
                continue;
            }
            if (containsAll(temp, requiredFields)) {
                amountA++;
                if (verify(temp)) {
                    amountB++;
                }
            }
            temp = "";
        }
        System.out.println(amountA);
        System.out.println(amountB);
    }

    public static boolean containsAll(String st, String... strings) {
        for (String s : strings) {
            if (!st.contains(s)) {
                return false;
            }
        }
        return true;
    }

    public static boolean verify(String s) {
        String[] temp = s.split("\n| ");
        for (String split : temp) {
            String left = split.substring(0, s.indexOf(":"));
            String right = split.substring(s.indexOf(":") + 1);
            if (!Field.valueOf(left).isValid(right)) {
                return false;
            }
        }
        return true;
    }
}
