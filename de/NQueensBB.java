 class NQueensBB {

    static int N = 4; // change this for different sizes

    static int board[][] = new int[N][N];

    // Arrays for Branch & Bound
    static boolean columns[] = new boolean[N];
    static boolean leftDiagonal[] = new boolean[2 * N];
    static boolean rightDiagonal[] = new boolean[2 * N];

    static boolean solve(int row) {
        if (row >= N)
            return true;

        for (int col = 0; col < N; col++) {

            if (!columns[col] &&
                    !leftDiagonal[row - col + N] &&
                    !rightDiagonal[row + col]) {

                // Place queen
                board[row][col] = 1;
                columns[col] = true;
                leftDiagonal[row - col + N] = true;
                rightDiagonal[row + col] = true;

                // Recur
                if (solve(row + 1))
                    return true;

                // Backtrack
                board[row][col] = 0;
                columns[col] = false;
                leftDiagonal[row - col + N] = false;
                rightDiagonal[row + col] = false;
            }
        }
        return false;
    }

    static void printBoard() {
        System.out.println("Solution for " + N + "-Queens:\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        if (solve(0))
            printBoard();
        else
            System.out.println("No solution exists");
    }
}
