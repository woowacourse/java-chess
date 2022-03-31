package chess.domain.piece;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;
import java.util.List;

public final class Bishop extends Piece {

    private static final String NAME = "b";
    private static final double SCORE = 3;

    private final Continuous continuous = new Continuous();

    public Bishop(Color color) {
        super(color, NAME, SCORE);
    }

    @Override
    public List<Position> getMovablePaths(Position source, ChessBoard board) {
        return continuous.getMovablePositions(Direction.diagonal(), source, board);
    }
}
