package chess.view;

import chess.domain.board.Board;
import chess.domain.piece.PieceColor;
import chess.domain.result.Score;
import chess.util.RenderingUtils;

public class OutputView {

    private static final String NEWLINE = System.lineSeparator();
    private static final String CHESS_GUIDE_MESSAGE = "체스 게임을 시작합니다.\n"
        + "게임 시작 : start\n"
        + "게임 종료 : end\n"
        + "게임 이동 : move source위치 target위치 - 예: move b2 b3\n"
        + "점수 확인 : status";
    private static final String INTRODUCE_PLAYER_TURN_MESSAGE = "의 차례입니다.";
    private static final String SCORE_IS_MESSAGE = "의 점수: ";
    private static final String END_GAME_MESSAGE = "게임이 종료되었습니다.";

    public static void printGuideMessage() {
        System.out.println(CHESS_GUIDE_MESSAGE + NEWLINE);
    }

    public static void printBoard(Board board) {
        System.out.println(NEWLINE + RenderingUtils.renderBoard(board));
    }

    public static void printTurn(PieceColor currentColor) {
        System.out.println(NEWLINE + currentColor.getColor() + INTRODUCE_PLAYER_TURN_MESSAGE);
    }

    public static void printFinishedMessage() {
        System.out.println(NEWLINE + END_GAME_MESSAGE);
    }

    public static void printError(IllegalArgumentException e) {
        System.out.println(NEWLINE + e.getMessage());
    }

    private static void printScore(PieceColor color, Score score) {
        System.out.println(color.getColor() + SCORE_IS_MESSAGE + score.is());
    }

    public static void printWinner(String winner) {
        System.out.println(winner);
    }

    public static void printStatus(Score whiteScore, Score blackScore) {
        printScore(PieceColor.WHITE, whiteScore);
        printScore(PieceColor.BLACK, blackScore);
    }

    public static void printPrevail(PieceColor prevailColor) {
        if (prevailColor.equals(PieceColor.NOTHING)) {
            System.out.println("동점입니다.");
            return;
        }
        System.out.println(prevailColor.getColor() + "이 우세합니다.");
    }
}
