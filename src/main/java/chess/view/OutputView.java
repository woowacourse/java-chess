package chess.view;

import chess.domain.board.Chessboard;
import chess.domain.piece.Color;

import java.util.Map;

public class OutputView {

    private static final String START_MESSAGE = "> 체스 게임을 시작합니다." +
            "\n> 게임 시작 : start" + "\n> 게임 종료 : end" +
            "\n> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String SCORE_MESSAGE = "status";
    private static final String BLACK = "블랙: ";
    private static final String WHITE = "화이트: ";
    private static final String BLANK = ".";

    public static void printStartMessage() {
        System.out.println(START_MESSAGE);
    }

    public static void printBoard(Chessboard chessboard) {
        for (int row = 0; row < Chessboard.SIZE.size(); row++) {
            printPiece(chessboard, row);
            System.out.println();
        }
        System.out.println();
    }

    private static void printPiece(Chessboard board, int row) {
        for (int column = 0; column < Chessboard.SIZE.size(); column++) {
            distinguishBlank(board, row, column);
        }
    }

    private static void distinguishBlank(Chessboard board, int row, int column) {
        if (board.isExistPosition(row, column)) {
            System.out.print(Symbol.findSymbol(board.findPiece(row, column)).symbol(board.findPiece(row, column)));
            return;
        }
        System.out.print(BLANK);
    }

    public static void printResult(Map<String, Object> scores) {
        Object scoreOfBlack = scores.get(Color.BLACK.name());
        Object scoreOfWhite = scores.get(Color.WHITE.name());

        System.out.println(SCORE_MESSAGE);
        System.out.println(BLACK + scoreOfBlack.toString());
        System.out.println(WHITE + scoreOfWhite.toString());
    }
}
