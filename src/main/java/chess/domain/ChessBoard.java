package chess.domain;

import chess.domain.piece.Position;

import java.util.ArrayList;
import java.util.List;

public class ChessBoard {
    private final static List<Position> POSITIONS;

    static {
        POSITIONS = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                POSITIONS.add(Position.of(i, j));
            }
        }
    }

    public static List<Position> getChessBoard() {
        return POSITIONS;
    }
}
