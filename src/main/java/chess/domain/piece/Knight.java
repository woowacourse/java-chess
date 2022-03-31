package chess.domain.piece;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;
import java.util.List;

public final class Knight extends Piece {

    private static final String NAME = "n";
    private static final double SCORE = 2.5;

    private final NonContinuous nonContinuous = new NonContinuous();

    public Knight(Color color) {
        super(color, NAME, SCORE);
    }

    @Override
    public List<Position> getMovablePaths(Position source, ChessBoard board) {
        return nonContinuous.getMovablePositions(source, board, Direction.getKnightDirections());
    }
}
