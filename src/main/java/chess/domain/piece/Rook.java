package chess.domain.piece;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.Player;

import java.util.Arrays;
import java.util.List;

public class Rook extends ChessPiece {

    private static final String NAME = "R";
    private static final List<Position> INITIAL_POSITIONS;

    static {
        INITIAL_POSITIONS = Arrays.asList(Position.of(Column.A, Row.ONE), Position.of(Column.H, Row.ONE));
    }

    public Rook(Player player) {
        super(NAME, player);
    }

    public static List<Position> getInitialPositions() {
        return INITIAL_POSITIONS;
    }
}
