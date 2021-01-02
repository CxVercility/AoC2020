

public class Day12 {

    static int north = 0;
    static int east = 0;
    static int wpn = 1;
    static int wpe = 10;
    static int direction = 90;

    public static void main(String[] args) {
        var input = AoCManager.getInput(12);
        int temp;
        for (String s : input) {
            temp = Integer.parseInt(s.substring(1));
            switchA(temp, s);

        }
        north = north < 0 ? -north : north;
        east = east < 0 ? -east : east;
        System.out.println(north + east);

        north = 0;
        east = 0;
        for (String s : input) {
            temp = Integer.parseInt(s.substring(1));
            switchB(temp, s);
        }
        north = north < 0 ? -north : north;
        east = east < 0 ? -east : east;
        System.out.println(north + east);
    }

    private static void switchA(int temp, String s) {
        switch (s.charAt(0)) {
            case 'N':
                north += temp;
                break;
            case 'E':
                east += temp;
                break;
            case 'S':
                north -= temp;
                break;
            case 'W':
                east -= temp;
                break;
            case 'L':
                direction -= temp;
                break;
            case 'R':
                direction += temp;
                break;
            case 'F':
                forward(temp);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static void switchB(int temp, String s) {
        switch (s.charAt(0)) {
            case 'N':
                wpn += temp;
                break;
            case 'E':
                wpe += temp;
                break;
            case 'S':
                wpn -= temp;
                break;
            case 'W':
                wpe -= temp;
                break;
            case 'L':
                rotate(-temp + 360);
                break;
            case 'R':
                rotate(temp);
                break;
            case 'F':
                forwardB(temp);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static void rotate(int amount) {

        int oldNorth = wpn;
        int oldEast = wpe;
        switch (amount) {
            case 90:
                wpe = oldNorth;
                wpn = -oldEast;
                break;
            case 180:
                wpn = -wpn;
                wpe = -wpe;
                break;
            case 270:
                wpe = -oldNorth;
                wpn = oldEast;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static void forwardB(int amount) {
        north += amount * wpn;
        east += amount * wpe;
    }

    private static void forward(int amount) {
        while (direction < 0) {
            direction += 360;
        }
        while (direction >= 360) {
            direction -= 360;
        }
        switch (direction) {
            case 0:
                north += amount;
                break;
            case 90:
                east += amount;
                break;
            case 180:
                north -= amount;
                break;
            case 270:
                east -= amount;
                break;
        }
    }
}
