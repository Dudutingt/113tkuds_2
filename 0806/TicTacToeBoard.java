public class TicTacToeBoard {
    private char[][] board;
    private char currentPlayer;

    public TicTacToeBoard() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // 嘗試放置棋子
    public boolean placeMark(int row, int col) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            if (board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                return true;
            }
        }
        return false;
    }

    // 換玩家
    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    // 檢查是否有玩家獲勝
    public boolean checkWin() {
        return (checkRows() || checkCols() || checkDiagonals());
    }

    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' &&
                board[i][0] == board[i][1] &&
                board[i][1] == board[i][2]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCols() {
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != ' ' &&
                board[0][j] == board[1][j] &&
                board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return ((board[0][0] != ' ' &&
                 board[0][0] == board[1][1] &&
                 board[1][1] == board[2][2]) ||
                (board[0][2] != ' ' &&
                 board[0][2] == board[1][1] &&
                 board[1][1] == board[2][0]));
    }

    
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }

    
    public static void main(String[] args) {
        TicTacToeBoard game = new TicTacToeBoard();
        java.util.Scanner scanner = new java.util.Scanner(System.in);

        System.out.println("井字遊戲開始！");
        game.printBoard();

        while (true) {
            System.out.println("輪到玩家 " + game.currentPlayer + "，請輸入行列 (0~2)：");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (game.placeMark(row, col)) {
                game.printBoard();

                if (game.checkWin()) {
                    System.out.println("玩家 " + game.currentPlayer + " 獲勝！");
                    break;
                } else if (game.isBoardFull()) {
                    System.out.println("平手！");
                    break;
                }

                game.changePlayer();
            } else {
                System.out.println("無效的位置，請重新輸入！");
            }
        }

        scanner.close();
    }
}
