package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.direction.Direction;
import chess.domain.piece.pathStrategy.PathStrategy;

public abstract class Pawn extends Piece {
    private static final String NAME = "p";
    public static final double PAWN_SCORE = 1;
    public static final double PAWN_HALF_SCORE = 0.5;

    public Pawn(PieceColor pieceColor, Position position, PathStrategy pathStrategy) {
        super(NAME, PAWN_SCORE, pieceColor, position, pathStrategy);
    }

    public Direction getDirection(Position targetPosition) {
        int xPointDirectionValue = this.position.getXPointDirectionValueTo(targetPosition);
        int yPointDirectionValue = this.position.getYPointDirectionValueTo(targetPosition);
        return Direction.of(xPointDirectionValue, yPointDirectionValue);
    }
}
