public class Day3 {

    public static void main(String[] args) {
        String[] inputs = AoCManager.getInput(3).toArray(new String[0]);
        int length = inputs[0].length();
        int height = inputs.length;
        int atrees = 0;
        int btrees = 0;
        int ctrees = 0;
        int dtrees = 0;
        int etrees = 0;
        char tree = '#';
        for (int i = 1; i < height; i++) {
            if (inputs[i].charAt((3 * i) % length) == tree) {
                atrees++;
            }
            if (inputs[i].charAt(i % length) == tree) {
                btrees++;
            }
            if (inputs[i].charAt((5 * i) % length) == tree) {
                ctrees++;
            }
            if (inputs[i].charAt((7 * i) % length) == tree) {
                dtrees++;
            }
            if (i % 2 == 0 && inputs[i].charAt((i / 2) % length) == tree) {
                etrees++;
            }

        }
        System.out.println((long) atrees * btrees * ctrees * dtrees * etrees);
    }
}
