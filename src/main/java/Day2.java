import java.util.List;

public class Day2 {

    public static void main(String[] args) {

        List<String> inputs = AoCManager.getInput(2);
        int valid = 0;
        int lower;
        int higher;
        char character;
        String temp;
        for (String s : inputs) {
            String[] temps = s.split("-| |:");
            lower = Integer.parseInt(temps[0]);
            higher = Integer.parseInt(temps[1]);
            character = temps[2].charAt(0);
            temp = temps[4];
             /*count = 0;
             for(int i = 0; i < temp.length(); i++){
                 if(temp.charAt(i) == character){
                     count++;
                 }
             }
             if(lower <= count && count <= higher){
                 valid++;
             }*/
            char one = temp.charAt(lower - 1);
            char two = temp.charAt(higher - 1);
            if (one == character ^ two == character) {
                valid++;
            }
        }
        System.out.println(valid);
    }
}
