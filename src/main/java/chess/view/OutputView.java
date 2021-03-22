package chess.view;

import chess.domain.board.Board;
import chess.domain.piece.PieceColor;
import chess.domain.result.Score;
import chess.util.RenderingUtils;

public class OutputView {

    private static final String NEWLINE = System.lineSeparator();

    public static void printGuideMessage() {
        System.out.println("체스 게임을 시작합니다.\n"
            + "게임 시작 : start\n"
            + "게임 종료 : end\n"
            + "게임 이동 : move source위치 target위치 - 예: move b2 b3"
            + NEWLINE);
    }

    public static void printBoard(Board board) {
        System.out.println(NEWLINE + RenderingUtils.renderBoard(board));
    }

    public static void printTurn(PieceColor currentColor) {
        System.out.println(NEWLINE + currentColor.getColor() + "의 차례입니다.");
    }

    public static void printFinishedMessage() {
        System.out.println(NEWLINE + "게임이 종료되었습니다.");
    }

    public static void printError(IllegalArgumentException e) {
        System.out.println(NEWLINE + e.getMessage());
    }

    public static void printScore(PieceColor color, Score score) {
        System.out.println(color.getColor() + "의 점수: " + score.is());
    }

    public static void printWinner(String winner) {
        System.out.println(winner);
    }

    public static void printStatus(Score whiteScore, Score blackScore) {
        printScore(PieceColor.WHITE, whiteScore);
        printScore(PieceColor.BLACK, blackScore);

    }
}
