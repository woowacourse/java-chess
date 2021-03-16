package chess;

import java.util.Arrays;

public class ChessBoard {

    private final String[][] chessBoard = new String[8][8];

    public ChessBoard() {
        int[] temp = {0, 7};
        for (int i : temp) {
            for (int j = 0; j < 8; j++) {
                if (j == 0 || j == 7) {
                    chessBoard[i][j] = "R";
                }
                if (j == 1 || j == 6) {
                    chessBoard[i][j] = "N";
                }
                if (j == 2 || j == 5) {
                    chessBoard[i][j] = "B";
                }
                if (j == 3) {
                    chessBoard[i][j] = "Q";
                }
                if (j == 4) {
                    chessBoard[i][j] = "K";
                }
            }
        }
        int[] pawn = {1, 6};
        for (int i : pawn) {
            for (int j = 0; j < 8; j++) {
                chessBoard[i][j] = "P";
            }
        }
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < 8; j++) {
                chessBoard[i][j] = ".";
            }
        }
        chessBoard[6] = Arrays.stream(chessBoard[6])
            .map(String::toLowerCase)
            .toArray(String[]::new);
        chessBoard[7] = Arrays.stream(chessBoard[7])
            .map(String::toLowerCase)
            .toArray(String[]::new);
        for (String[] array : chessBoard) {
            for (String board : array) {
                System.out.print(board);
            }
            System.out.println();
        }
    }

    public String[][] getChessBoard() {
        return chessBoard;
    }
}
