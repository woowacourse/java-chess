package chess.view;

import chess.domain.piece.Piece;
import chess.domain.square.Square;

import java.util.Arrays;
import java.util.Map;

public class OutputView {

    private static final int BOARD_SIZE = 8;
    private static final char EMPTY_PIECE = '.';
    private static final String TITLE_START = "> 체스 게임을 시작합니다.%n" +
            "> 게임 시작 : start%n" +
            "> 게임 종료 : end%n" +
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3%n";
    private static final String ERROR_PREFIX = "[ERROR] ";

    public static void printStartMessage() {
        System.out.printf(TITLE_START);
    }

    public static void printBoard(final Map<Square, Piece> board) {
        final char[][] result = generateEmptyBoard();
        setPiecesOnBoard(board, result);
        printResultBoard(result);
    }

    private static char[][] generateEmptyBoard() {
        final char[][] emptyBoard = new char[BOARD_SIZE][BOARD_SIZE];
        for (char[] line : emptyBoard) {
            Arrays.fill(line, EMPTY_PIECE);
        }
        return emptyBoard;
    }

    private static void setPiecesOnBoard(final Map<Square, Piece> board, final char[][] result) {
        board.forEach(((square, piece) -> {
            int col = square.file().getIndex() - 1;
            int row = square.rank().getIndex() - 1;
            result[row][col] = PieceMapper.map(piece.getType(), piece.getColor());
        }));
    }

    private static void printResultBoard(final char[][] result) {
        for (int i = result.length - 1; i >= 0; i--) {
            System.out.println(String.copyValueOf(result[i]));
        }
        System.out.println();
    }
}
