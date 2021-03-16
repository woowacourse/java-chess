package chess;

import java.util.Arrays;

public class ChessBoard {

    private final String[][] chessBoard = new String[8][8];

    public ChessBoard() {
        initPiece();
        initPawn();
        initBlank();
        toLowerCase();
        for (String[] array : chessBoard) {
            for (String board : array) {
                System.out.print(board);
            }
            System.out.println();
        }
    }

    private void initPiece() {
        int[] row = {0, 7};
        for (int i : row) {
            chessBoard[i] = new String[]{"R", "N", "B", "Q", "K", "B", "N", "R"};
        }
    }

    private void initPawn() {
        int[] row = {1, 6};
        for (int i : row) {
            chessBoard[i] = new String[]{"P", "P", "P", "P", "P", "P", "P", "P"};
        }
    }

    private void initBlank() {
        for (int i = 2; i < 6; i++) {
            chessBoard[i] = new String[]{".", ".", ".", ".", ".", ".", ".", "."};
        }
    }

    private void toLowerCase() {
        chessBoard[6] = Arrays.stream(chessBoard[6])
            .map(String::toLowerCase)
            .toArray(String[]::new);
        chessBoard[7] = Arrays.stream(chessBoard[7])
            .map(String::toLowerCase)
            .toArray(String[]::new);
    }

    public String[][] getChessBoard() {
        return chessBoard;
    }
}
