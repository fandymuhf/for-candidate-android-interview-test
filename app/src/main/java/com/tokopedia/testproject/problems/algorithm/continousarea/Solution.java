package com.tokopedia.testproject.problems.algorithm.continousarea;

import android.util.Log;

import java.util.Stack;

/**
 * Created by hendry on 18/01/19.
 */
public class Solution {
    public static int maxContinuousArea(int[][] matrix) {
        // TODO, return the largest continuous area containing the same integer, given the 2D array with integers
        // below is stub
        boolean[][] seen = new boolean[matrix.length][matrix[0].length];
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        int ans = 0;
        int hasil = 0;

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                int targetval = matrix[i][j];

                for (int r0 = 0; r0 < matrix.length; r0++) {
                    for (int c0 = 0; c0 < matrix[0].length; c0++) {
                        if (matrix[r0][c0] == targetval && !seen[r0][c0]) {
                            int shape = 0;
                            Stack<int[]> stack = new Stack();
                            stack.push(new int[]{r0, c0});
                            seen[r0][c0] = true;
                            while (!stack.empty()) {
                                int[] node = stack.pop();
                                int r = node[0], c = node[1];
                                shape++;
                                for (int k = 0; k < 4; k++) {
                                    int nr = r + dr[k];
                                    int nc = c + dc[k];
                                    if (0 <= nr && nr < matrix.length &&
                                            0 <= nc && nc < matrix[0].length &&
                                            matrix[nr][nc] == targetval && !seen[nr][nc]) {
                                        stack.push(new int[]{nr, nc});
                                        seen[nr][nc] = true;
                                    }
                                }
                            }
                            ans = Math.max(ans, shape);
                            if(hasil<ans)hasil = ans;
                        }
                    }
                }
            }
        }
        System.out.println("Output: "+hasil);
        return hasil;

    }
}
