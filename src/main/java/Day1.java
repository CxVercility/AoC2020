
public class Day1 {

    public static void main(String[] args) {
        String[] inputs = new AoCManager().getInput(1).toArray(new String[0]);
        for (int i = 0; i < inputs.length; i++) {
            for (int j = i; j < inputs.length; j++) {
                for (int k = j; k < inputs.length; k++) {
                    if (Integer.parseInt(inputs[i]) + Integer.parseInt(inputs[j]) + Integer.parseInt(inputs[k]) == 2020) {
                        System.out.println(Integer.parseInt(inputs[k]) * Integer.parseInt(inputs[i]) * Integer.parseInt(inputs[j]));
                    }
                }

            }
        }
    }
}
