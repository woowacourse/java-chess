package chess.view;

import chess.domain.board.Board;
import chess.domain.game.ScoreResult;
import chess.domain.board.Rows;
import chess.domain.piece.Color;

public class OutputView {
    private static final String OPERATIONS_MESSAGE = String.join("\n",
            "> 체스 게임을 시작합니다.",
            "> 게임 시작 : start",
            "> 게임 종료 : end",
            "> 게임 이동 : move source위치 target위치 - 예. move b2 b3",
            "> 점수 확인 : status");

    public static void printOperationsFormat() {
        System.out.println(OPERATIONS_MESSAGE);
    }

    public static void printBoard(Board board) {
        printEmptyLine();

        for(Rows rows : board.getRows()) {
            System.out.println(rows.getResources());
        }

        printEmptyLine();
    }

    public static void printFinishByKingDead(Color color) {
        System.out.println(color + "가 승리하였습니다.");
    }

    private static void printEmptyLine() {
        System.out.println();
    }

    public static void printFinish() {
        System.out.println("체스 게임을 종료합니다.");
    }

    public static void printScore(ScoreResult scores) {
        printEmptyLine();
        for (Color color : scores.keySet()) {
            System.out.println(color + "의 점수는 " + scores.getScoreBy(color) + "점 입니다.");
        }
    }
}
