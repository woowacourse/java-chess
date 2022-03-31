package chess.view;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.Set;

public class OutputView {

    private static final int LINE_BREAK_UNIT = 8;

    public static void printStartGame() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source 위치 target 위치 - 예. move b2 b3");
    }

    public static void printBoard(final Map<Position, Piece> board) {
        System.out.println(loadBoardView(board));
    }

    private static String loadBoardView(final Map<Position, Piece> board) {
        StringBuilder stringBuilder = new StringBuilder();
        Set<Position> keys = board.keySet();
        int unit = 1;
        for (Position position : keys) {
            Piece piece = board.get(position);
            stringBuilder.append(PieceSymbol.getSymbol(piece));
            unit = appendNewLine(stringBuilder, unit);
        }
        return stringBuilder.toString();
    }

    private static int appendNewLine(final StringBuilder stringBuilder, int unit) {
        if (unit % LINE_BREAK_UNIT == 0) {
            stringBuilder.append("\n");
            return 1;
        }
        return ++unit;
    }

    public static void printEndMessage() {
        System.out.println("게임이 종료됐습니다.");
    }

    public static void printScore(final double whiteScore, final double blackScore, final String winColor) {
        System.out.printf("백팀 점수 : %.1f\n", whiteScore);
        System.out.printf("흑팀 점수 : %.1f\n", blackScore);
        System.out.printf("승리팀 : %s\n", winColor);
    }
}
