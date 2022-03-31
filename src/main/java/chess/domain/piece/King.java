package chess.domain.piece;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;
import java.util.List;

public final class King extends Piece {

    private static final String NAME = "k";
    private static final double SCORE = 0;

    private final NonContinuous nonContinuous = new NonContinuous();

    public King(Color color) {
        super(color, NAME, SCORE);
    }

    @Override
    public List<Position> getMovablePaths(Position source, ChessBoard board) {
        return nonContinuous.getMovablePositions(source, board, Direction.all());
    }
}
