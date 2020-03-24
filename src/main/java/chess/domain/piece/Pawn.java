package chess.domain.piece;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends ChessPiece {

    private static final String NAME = "P";
    private static final List<Position> INITIAL_POSITIONS;

    static {
        INITIAL_POSITIONS = new ArrayList<>();
        for (Column column : Column.values()) {
            INITIAL_POSITIONS.add(Position.of(column, Row.TWO));
        }
    }

    public Pawn(Player player) {
        super(NAME, player);
    }

    public static List<Position> getInitialPositions() {
        return INITIAL_POSITIONS;
    }
}
