package chess.view;

import chess.domain.board.Chessboard;

public class OutputView {

    private static final String START_MESSAGE = "> 체스 게임을 시작합니다." +
            "\n> 게임 시작 : start" + "\n> 게임 종료 : end" +
            "\n> 게임 이동 : move source위치 target위치 - 예. move b2 b3";
    private static final String SCORE_MESSAGE = "획득 점수";
    private static final String BLACK = "블랙";
    private static final String WHITE = "화이트";
    private static final String DELIMITER = " : ";
    private static final String WINNER = "우승";
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
            if (board.isExistKey(row, column)) {
                System.out.print(Symbol.findSymbol(board.getPosition(row, column)).symbol());
                continue;
            }
            System.out.print(BLANK);
        }
    }

    public static void printProgressScore(double scoreOfBlack, double scoreOfWhite) {
        System.out.println(SCORE_MESSAGE);
        System.out.println(BLACK + DELIMITER + scoreOfBlack);
        System.out.println(WHITE + DELIMITER + scoreOfWhite);
    }

    public static void printFinalScore(double scoreOfBlack, double scoreOfWhite) {
        printProgressScore(scoreOfBlack, scoreOfWhite);
        if (scoreOfWhite > scoreOfBlack) {
            System.out.println(WINNER + DELIMITER + WHITE);
        }
        System.out.println(WINNER + DELIMITER + BLACK);
    }
}
