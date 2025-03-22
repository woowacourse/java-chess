package chess.view;

import chess.Board;
import chess.Column;
import chess.Position;
import chess.Row;
import chess.piece.Piece;
import java.util.Arrays;

public class BoardView {
    private static final int MAX_COLUMN = 8;
    private static final int MAX_ROW = 8;

    private static final String GREEN_CODE = "\u001B[32m";
    private static final String WHITE_CODE = "\u001B[37m";
    private static final String BLACK_CODE = "\u001B[30m";
    private static final String COLOR_FINISH_CODE = "\u001B[0m";


    private static final String[][] matrix = new String[MAX_ROW + 1][MAX_COLUMN + 1];

    public static void printBoard(Board board) {
        clearBoard();
        for (Piece piece : board.getPieces()) {
            Position position = piece.getPosition();
            matrix[position.getRow() + 1][position.getColumn() + 1] = getPieceNameMessage(piece);
        }

        for (String[] row : matrix) {
            System.out.println(String.join(" | ", row));
        }
    }

    private static String getPieceNameMessage(Piece piece) {
        if (piece.isBlack()) {
            return GREEN_CODE + piece.getName() + COLOR_FINISH_CODE;
        }
        return WHITE_CODE + piece.getName() + COLOR_FINISH_CODE;
    }

    private static void clearBoard() {
        for (int row = 0; row < MAX_ROW + 1; row++) {
            Arrays.fill(matrix[row], BLACK_CODE + "ㅁ" + COLOR_FINISH_CODE);
            if (row == 0) {
                for (int column = 0; column < MAX_COLUMN; column++) {
                    matrix[row][column + 1] = Column.ofOrdinal(column);
                }
                continue;
            }
            matrix[row][0] = Row.ofOrdinal(row - 1);
        }
    }
}
