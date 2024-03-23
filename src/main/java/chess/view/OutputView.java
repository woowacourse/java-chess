package chess.view;

import chess.domain.piece.Piece;
import chess.domain.square.Square;

import java.util.Arrays;
import java.util.Set;

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

    public static void printBoard(Set<Piece> pieces) {
        char[][] board = generateEmptyBoard();
        setPiecesOnBoard(board, pieces);
        for (char[] line : board) {
            System.out.println(line);
        }
        System.out.println();
    }

    private static char[][] generateEmptyBoard() {
        char[][] emptyBoard = new char[BOARD_SIZE][BOARD_SIZE];
        for (char[] line : emptyBoard) {
            Arrays.fill(line, EMPTY_PIECE);
        }
        return emptyBoard;
    }

    private static void setPiecesOnBoard(char[][] board, Set<Piece> pieces) {
        for (Piece piece : pieces) {
            Square square = piece.getSquare();
            char pieceSymbol = PieceMapper.map(piece.getType(), piece.getColor());
            board[square.getFileOrdinal()][square.getRankOrdinal()] = pieceSymbol;
        }
    }

    public static void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }
}
