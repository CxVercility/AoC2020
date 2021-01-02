public class Day17 {

    static boolean[][][] cubes;
    static boolean[][][] cloned;

    static boolean cubeAt(int x, int y, int z) {
        if (x + ROUNDS < 0 || y + ROUNDS < 0 || z + ROUNDS < 0 || x + ROUNDS >= xSize || y + ROUNDS >= zSize || z + ROUNDS >= zSize) {
            return false;
        }
        return cubes[x + ROUNDS][y + ROUNDS][z + ROUNDS];
    }

    static void setCubeAt(int x, int y, int z, boolean active) {
        cloned[x + ROUNDS][y + ROUNDS][z + ROUNDS] = active;
    }

    static final int ROUNDS = 6;
    static int xSize;
    static int ySize;
    static int zSize;

    public static void main(String[] args) {
        var input = AoCManager.getInput(17);
        final int length = input.get(0).length();
        final int size = input.size();
        final int xDiameter = length / 2;
        final int yDiameter = size / 2;
        xSize = length + 2 * ROUNDS;
        ySize = size + 2 * ROUNDS;
        zSize = 1 + 2 * ROUNDS;
        cubes = new boolean[xSize][ySize][zSize];
        cloned = cubes.clone();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < length; x++) {
                if (input.get(y).charAt(x) == '#') {
                    cubes[ROUNDS - xDiameter + x][ROUNDS - yDiameter + y][0] = true;
                }
            }
        }
        for (int round = 1; round <= ROUNDS; round++) {
            for (int x = -round + xDiameter; x <= round + xDiameter; x++) {
                for (int y = -round + yDiameter; y <= round + yDiameter; y++) {
                    for (int z = -round; z <= round; z++) {
                        int activeNeighbours = checkNeighbours(x, y, z);
                        if (cubeAt(x, y, z) && (activeNeighbours < 2 || activeNeighbours > 3)) {
                            setCubeAt(x, y, z, false);
                        } else if (!cubeAt(x, y, z) && activeNeighbours == 3) {
                            setCubeAt(x, y, z, true);
                        }
                    }
                }
            }
            cubes = cloned.clone();
        }
        int res = 0;
        for (int i = 0; i < cubes.length; i++) {
            for (int i1 = 0; i1 < cubes[0].length; i1++) {
                for (int i2 = 0; i2 < cubes[0][0].length; i2++) {
                    if (cubeAt(i, i1, i2)) {
                        res++;
                    }
                }
            }
        }
        System.out.println(res);
    }

    static int checkNeighbours(int x, int y, int z) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int i1 = -1; i1 <= 1; i1++) {
                for (int i2 = -1; i2 <= 1; i2++) {
                    if (i == x && i1 == y && i2 == z) {
                        continue;
                    }
                    if (cubeAt(x + i, y + i1, z + i2)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
}