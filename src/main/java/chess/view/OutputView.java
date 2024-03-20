package chess.view;

import chess.domain.piece.Piece;
import chess.domain.Position;

import java.util.Arrays;
import java.util.Map;

public class OutputView {
    private static final int BOARD_SIZE = 8;
    private static final char EMPTY_PIECE = '.';

    public static void printInitialBoard(final Map<Position, Piece> board) {
        char[][] result = generateEmptyBoard();
        setPiecesOnBoard(board, result);
        printBoard(result);
    }

    private static char[][] generateEmptyBoard() {
        char[][] emptyBoard = new char[BOARD_SIZE][BOARD_SIZE];
        for (char[] line : emptyBoard) {
            Arrays.fill(line, EMPTY_PIECE);
        }
        return emptyBoard;
    }

    private static void setPiecesOnBoard(final Map<Position, Piece> board, final char[][] result) {
        board.forEach(((position, piece) -> {
            int col = position.file().get() - 1;
            int row = position.rank().get() - 1;
            result[row][col] = PieceMapper.map(piece.getName(), piece.getColor());
        }));
    }

    private static void printBoard(final char[][] result) {
        for (int i = result.length - 1; i >= 0; i--) {
            System.out.println(String.copyValueOf(result[i]));
        }
    }
}
