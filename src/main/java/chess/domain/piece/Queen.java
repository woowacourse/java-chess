package chess.domain.piece;

import chess.domain.board.Column;
import chess.domain.board.Position;
import chess.domain.board.Row;
import chess.domain.player.Player;

import java.util.Arrays;
import java.util.List;

public class Queen extends ChessPiece {

    private static final String NAME = "Q";
    private static final List<Position> INITIAL_POSITIONS;

    static {
        INITIAL_POSITIONS = Arrays.asList(Position.of(Column.D, Row.ONE));
    }

    public Queen(Player player) {
        super(NAME, player);
    }

    public static List<Position> getInitialPositions() {
        return INITIAL_POSITIONS;
    }
}
