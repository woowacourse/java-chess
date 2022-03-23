package chess.view;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import java.util.Map;
import java.util.Set;

public class OutputView {

    private static final int LINE_BREAK_UNIT = 8;

    public static void printStartGame() {
        System.out.println("체스 게임을 시작합니다.");
    }

    public static void printBoard(final Map<Position, Piece> board) {
        System.out.println(loadBoard(board));
    }

    private static String loadBoard(final Map<Position, Piece> board) {
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

    private static int appendNewLine(StringBuilder stringBuilder, int unit) {
        if (unit % LINE_BREAK_UNIT == 0) {
            stringBuilder.append("\n");
            return 1;
        }
        return ++unit;
    }
}
