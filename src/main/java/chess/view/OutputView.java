package chess.view;

import chess.domain.Board;
import chess.domain.piece.PieceColor;
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
        System.out.println(RenderingUtils.renderBoard(board));
    }

    public static void printError(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public static void printPlayerTurn(PieceColor currentColor) {
        System.out.println(currentColor.getColor() + "의 차례입니다.");
    }
}
