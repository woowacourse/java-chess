package chess.domain.grid;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private static final int FIRST_ROW = 1;
    private static final int SECOND_ROW = 2;
    private static final int THIRD_ROW = 3;
    private static final int SIXTH_ROW = 6;
    private static final int SEVENTH_ROW = 7;
    private static final int EIGHTH_ROW = 8;
    private static final int GAP_BETWEEN_INDEX_ACTUAL = 1;
    private static List<Line> lines;

    static {
        initialize();
    }

    public static void initialize() {
        lines = new ArrayList<>();
        lines.add(Line.createGeneralLine(FIRST_ROW, false));
        lines.add(Line.createPawnLine(SECOND_ROW, false));
        for (int i = THIRD_ROW; i <= SIXTH_ROW; i++) {
            lines.add(Line.createEmptyLine(i));
        }
        lines.add(Line.createPawnLine(SEVENTH_ROW, true));
        lines.add(Line.createGeneralLine(EIGHTH_ROW, true));
    }

    public static boolean isOccupied(final Position position) {
        boolean b = findPiece(position) instanceof Empty;
        return b;
    }

//    public void move(Position source, Position target) {
//        // 해당 위치의 체스말 가져오기
//        lines.get(source).move(target);
//
//        // 움직이고자 하는 곳의 체스말 바꾸기
////        lines[source].set(target);
////        lines[taget].set(source);
//    }

    private static Piece findPiece(final Position position) {
        char x = position.getX();
        char y = position.getY();
        int yIndex = Character.getNumericValue(y) - GAP_BETWEEN_INDEX_ACTUAL;
        Line line = lines.get(yIndex);
        return line.findPiece(x);
    }
}
