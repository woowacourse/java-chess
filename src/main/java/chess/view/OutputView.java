package chess.view;

import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;

import java.util.Arrays;

public class OutputView {

    public static final int BOARD_MAX_SIZE = 8;

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
    }

    public static void printBoard(final Board board) {
        String[][] boardStatus = getInitBoard();
        putPieceToBoard(board, boardStatus);

        for (int boardSize = 0; boardSize < BOARD_MAX_SIZE; ++boardSize) {
            printOneLine(boardStatus[boardSize]);
        }
    }

    private static void putPieceToBoard(final Board board, final String[][] arr) {
        board.toMap().values().forEach(v -> {
            for (Piece piece : v) {
                Position position = piece.getPosition();
                arr[position.getRow()][position.getCol()] = piece.getInitial();
            }
        });
    }

    private static String[][] getInitBoard() {
        String[][] arr = new String[8][8];

        for (int i = 0; i < 8; ++i) {
            Arrays.fill(arr[i], ".");
        }
        return arr;
    }

    private static void printOneLine(String[] line) {
        for (String val : line) {
            System.out.print(val);
        }
        System.out.println();
    }
}
