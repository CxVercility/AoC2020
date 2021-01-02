import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class Day14 {

    private static int MASK_LENGTH = 36;

    private static int lul = 24;

    public static void main(String[] args) {
        DoRandomShit(false);
        DoRandomShit(true);
    }

    private static void DoRandomShit(boolean b) {
        var input = AoCManager.getInput(14);
        char[] mask = new char[MASK_LENGTH];
        String[] split = null;
        char[] charArray = null;
        char[] result = new char[MASK_LENGTH];
        Map<Long, Long> memory = new HashMap<>();
        for (String s : input) {
            for (int i = 0; i < MASK_LENGTH; i++) {
                result[i] = '0';
            }
            split = s.replaceAll(" ", "").split("=");
            if (s.contains("mask")) {
                charArray = split[1].toCharArray();
                for (int i = 0; i < charArray.length; i++) {
                    mask[MASK_LENGTH - charArray.length + i] = charArray[i];
                }
            } else {
                String indexString = split[0].replaceAll("mem|\\[|]", "");
                Long index = Long.parseLong(indexString);
                String number;
                if (!b) {
                    number = Long.toBinaryString(Long.parseLong(split[1]));
                    charArray = number.toCharArray();
                    PartA(mask, charArray, result, memory, index);
                } else {
                    number = Long.toBinaryString(index);
                    charArray = number.toCharArray();
                    PartB(mask, charArray, result, memory, Long.parseUnsignedLong(split[1]));
                }
            }
        }
        long res = 0;
        for (Long l : memory.values()) {
            res += l;
        }
        System.out.println(res);
    }

    private static void PartB(char[] mask, char[] charArray, char[] result, Map<Long, Long> memory, Long value) {
        for (int i = 0; i < charArray.length; i++) {
            result[MASK_LENGTH - charArray.length + i] = charArray[i];
        }
        for (int i = 0; i < MASK_LENGTH; i++) {
            if (mask[i] == '0') {
                continue;
            }
            result[i] = mask[i];
        }
        Queue<char[]> combinations = new ArrayDeque<>();
        combinations.add(result);
        boolean changed = true;
        char[] next;
        while ((next = combinations.peek()) != null && changed) {
            changed = false;
            for (int i = 0; i < MASK_LENGTH; i++) {
                if (next[i] == 'X') {
                    changed = true;
                    char[] temp = next.clone();
                    temp[i] = '0';
                    combinations.add(temp);
                    temp = temp.clone();
                    temp[i] = '1';
                    combinations.add(temp);
                    combinations.remove(next);
                    break;
                }
            }
        }
        for (char[] c : combinations) {
            memory.put(Long.parseUnsignedLong(new String(c), 2), value);
        }
    }

    private static void PartA(char[] mask, char[] charArray, char[] result, Map<Long, Long> memory, Long index) {
        for (int i = 0; i < charArray.length; i++) {
            result[MASK_LENGTH - charArray.length + i] = charArray[i];
        }
        for (int i = 0; i < MASK_LENGTH; i++) {
            if (mask[i] != 'X') {
                result[i] = mask[i];
            }
        }
        memory.put(index, Long.parseUnsignedLong(new String(result), 2));
    }
}
