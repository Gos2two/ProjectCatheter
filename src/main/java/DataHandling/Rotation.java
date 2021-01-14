package DataHandling;

import java.util.Arrays;

public class Rotation {
    private int num_rows;
    private int num_cols;
    private int[][] rot_matrix;
    private int counter;
    private int num_rings;

    public Rotation(int rows, int cols){
        num_cols = cols;
        num_rows = rows;
        init_rings();
        reset_matrix();
    }

    public int[][] getRot_matrix() {
        return rot_matrix;
    }

    private void init_rings() {
        num_rings = (int) Math.floor((num_rows+num_cols)/ 4);
    }

    private void reset_matrix() {
        rot_matrix = new int[num_rows][num_cols];
        counter =0;

        for (int i = 0; i < num_rows; i++) {
            for (int j = 0; j < num_cols; j++) {
                rot_matrix[i][j] = i*num_cols +j +1;
            }
        }
    }

    public void rotate() {
        rotate_matrix(0);
        counter++;
        for (int i = 1; i < num_rings; i++) {
            if((counter % (3*i)) == 0) {
                rotate_matrix(i);
            }
        }
    }

    private void rotate_matrix(int c) {
        //Corner elements
        int rows = num_rows - c;
        int cols = num_cols - c;
        int top_left = rot_matrix[c][c];
        int top_right = rot_matrix[c][cols - 1];
        int bot_right = rot_matrix[rows - 1][cols - 1];
        int bot_left = rot_matrix[rows - 1][c];
        int prev;
        int curr;

        //First row
        prev = top_left;
        for (int i = c + 1; i < cols; i++) {
            curr = rot_matrix[c][i];
            rot_matrix[c][i] = prev;
            prev = curr;
        }

        prev = top_right;
        //Last column
        for (int i = c + 1; i < rows; i++) {
            curr = rot_matrix[i][cols - 1];
            rot_matrix[i][cols - 1] = prev;
            prev = curr;
        }

        //Last Row
        prev = bot_right;
        for (int i = 2; i <= cols - c; i++) {
            curr = rot_matrix[rows - 1][cols - i];
            rot_matrix[rows - 1][cols - i] = prev;
            prev = curr;
        }

        //First col
        prev = bot_left;
        for (int i = 2; i <= rows - c; i++) {
            curr = rot_matrix[rows - i][c];
            rot_matrix[rows - i][c] = prev;
            prev = curr;
        }
    }
}
