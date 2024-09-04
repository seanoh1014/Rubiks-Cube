import java.util.*;

public class Cube {

    private int[][][] cube;
    private int[] numRotate;

    public Cube() {
        cube = new int[6][3][3];
        int num = 0;
        for (int[][] side : cube) {
            num++;
            for (int row = 0; row < side.length; row++) {
                for (int col = 0; col < side[row].length; col++) {
                    side[row][col] = num++;
                }
            }
        }
        numRotate = new int[6];
    }

    /**
    * rotate the given side of the cube 90 degrees to the right.
    */
    public void rotateCube(int side) {
        // array that has numbers of one side
        int[][] sideArr = cube[side];
        
        // duplicate sideArr
        int[][] secondCube = new int[3][3];

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                secondCube[i][j] = sideArr[i][j];
            }
            System.out.println();
        }
        // change numbers on the side manually
        // top left --> top right
        sideArr[0][2] = secondCube[0][0]; sideArr[1][2] = secondCube[0][1];

        // top right --> bottom right
        sideArr[2][2] = secondCube[0][2]; sideArr[2][1] = secondCube[1][2];

        // bottom right --> bottom left
        sideArr[2][0] = secondCube[2][2]; sideArr[1][0] = secondCube[2][1];
        
        // bottom left --> top left
        sideArr[0][0] = secondCube[2][0]; sideArr[0][1] = secondCube[1][0];

        // change adjacent sides
        // duplicate cube
        int[][][] dupCube = new int[6][3][3];

        for (int i=0; i<6; i++) {
            for (int j=0; j<3; j++) {
                for (int k=0; k<3; k++) {
                    dupCube[i][j][k] = cube[i][j][k];
                }
            }
        }
        // if top
        if (side == 0) {
            for (int i=1; i<4; i++) {
                cube[i][0] = dupCube[i+1][0];
            }
            // for right side, 
            cube[4][0] = dupCube[1][0];
            return;
        }
        // if bottom
        if (side == 5) {
            for (int i=2; i<5; i++) {
                cube[i][2] = dupCube[i-1][2];
            }
            // for left side, 
            cube[1][2] = dupCube[4][2];
            return;
        }
        // if left,
        if (side == 1) {
            // back --> front
            for (int i=0; i<3; i++) {
                cube[0][i][0] = dupCube[4][2-i][2];
            }
            // top --> front
            for (int i=0; i<3; i++) {
                cube[2][i][0] = dupCube[0][i][0];
            }
            // front --> bottom
            for (int i=0; i<3; i++) {
                cube[5][i][0] = dupCube[2][i][0];
            }
            // bottom --> back
            for (int i=0; i<3; i++) {
                cube[4][i][2] = dupCube[5][2-i][0];
            }
            return;
        }
        // if right,
        if (side == 3) {
            // back --> bottom
            for (int i=0; i<3; i++) {
                cube[5][i][2] = dupCube[4][2-i][0];
            }
            // bottom --> front
            for (int i=0; i<3; i++) {
                cube[2][i][2] = dupCube[5][i][2];
            }
            // front --> top
            for (int i=0; i<3; i++) {
                cube[0][i][2] = dupCube[2][i][2];
            }
            // top --> back
            for (int i=0; i<3; i++) {
                cube[4][i][0] = dupCube[0][2-i][2];
            }
            return;
        }
        // if front, 
        if (side == 2) {
            // 1 --> 0
            for (int i=0; i<3; i++) {
                cube[0][2][2-i] = dupCube[1][i][2];
            }
            // 0 --> 3
            for (int i=0; i<3; i++) {
                cube[3][i][0] = dupCube[0][2][i];
            }
            // 3 --> 5
            for (int i=0; i<3; i++) {
                cube[5][0][i] = dupCube[3][2-i][0];
            }
            // 5--> 1
            for (int i=0; i<3; i++) {
                cube[1][i][2] = dupCube[5][0][i];
            }
            return;
        }
        // if back,
        if (side == 4) {
            // 0 --> 1
            for (int i=0; i<3; i++) {
                cube[1][i][0] = dupCube[0][0][2-i];
            }
            // 1 --> 5
            for (int i=0; i<3; i++) {
                cube[5][2][i] = dupCube[1][i][0];
            }
            // 5 --> 3
            for (int i=0; i<3; i++) {
                cube[3][i][2] = dupCube[5][2][2-i];
            }
            // 3 --> 0
            for (int i=0; i<3; i++) {
                cube[0][0][i] = dupCube[3][i][2];
            }
            return;
        }
        
    
    }

    public int[] reverse(int[] array) {
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }
        return array;
    }

    public int[][][] getCube() {
        return cube;
    }
    
    public String toString() {
        String str = "\n";

        // top of cube
        for (int row = 0; row < 3; row++) {
            str += "\t\t";
            for (int col = 0; col < 3; col++) {
                str += setColor(cube[0][row][col]);
            }
            str += "\n";
        }
        str += "\n";

        // sides
        for (int row = 0; row < 3; row++) {
            for (int side = 1; side < 5; side++) {
                for (int col = 0; col < 3; col++) {
                    str += setColor(cube[side][row][col]);
                }
                str += "\t";
            }
            str += "\n";
        }
        str += "\n";

        // bottom
        for (int row = 0; row < 3; row++) {
            str += "\t\t";
            for (int col = 0; col < 3; col++) {
                str += setColor(cube[5][row][col]);
            }
            str += "\n";
        }
        return str + "\n\n\033[0m";
    }

    public String setColor(int num) {
        if (num < 10 ){
            return "\033[38;5;7m" + String.format("%02d ", num); // white
        } else if (num < 20 ){
            return "\033[38;5;208m" + String.format("%02d ", num); // orange
        }else if (num < 30 ){
            return "\033[38:5:40m" + String.format("%02d ", num); // green
        }else if (num < 40 ){
            return "\033[38:5:196m" + String.format("%02d ", num); // red
        }else if (num < 50 ){
            return "\033[38;5;27m" + String.format("%02d ", num); // blue
        }else {   
            return "\033[38;5;226m" + String.format("%02d ", num); // yellow
        }
    }

}
