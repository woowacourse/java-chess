package chess.domain.piece;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.Player;

import java.util.Arrays;
import java.util.List;

public class Bishop extends ChessPiece {

    private static final String NAME = "B";
    private static final List<Position> INITIAL_POSITIONS;

    static {
        INITIAL_POSITIONS = Arrays.asList(Position.of(Column.C, Row.ONE), Position.of(Column.F, Row.ONE));
    }

    public Bishop(Player player) {
        super(NAME, player);
    }

    public static List<Position> getInitialPositions() {
        return INITIAL_POSITIONS;
    }
}
