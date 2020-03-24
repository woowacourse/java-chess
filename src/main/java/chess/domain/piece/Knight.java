package chess.domain.piece;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.Player;

import java.util.Arrays;
import java.util.List;

public class Knight extends ChessPiece {

    private static final String NAME = "N";
    private static final List<Position> INITIAL_POSITIONS;

    static {
        INITIAL_POSITIONS = Arrays.asList(Position.of(Column.B, Row.ONE), Position.of(Column.G, Row.ONE));
    }

    public Knight(Player player) {
        super(NAME, player);
    }

    public static List<Position> getInitialPositions() {
        return INITIAL_POSITIONS;
    }
}
