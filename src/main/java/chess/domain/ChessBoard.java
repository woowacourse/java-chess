package chess.domain;

import chess.domain.piece.Position;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private final static String ROW = "abcdefgh";
    private final static String COL = "12345678";
    private final static List<Position> POSITIONS;

    static {
        POSITIONS = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                POSITIONS.add(Position.of(ROW.charAt(i), COL.charAt(j)));
            }
        }
    }

    public static List<Position> getChessBoard() {
        return POSITIONS;
    }
}
