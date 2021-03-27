package chess.domain.piece;

import chess.domain.board.Path;
import chess.domain.piece.strategy.Direction;
import chess.domain.piece.strategy.PieceStrategy;
import chess.domain.position.Position;
import java.util.List;

public final class RealPiece implements Piece {

    private final PieceColor color;
    private final PieceStrategy pieceStrategy;

    protected RealPiece(final PieceColor color, final PieceStrategy pieceStrategy) {
        this.color = color;
        this.pieceStrategy = pieceStrategy;
    }

    @Override
    public List<Direction> directions() {
        return pieceStrategy.directions();
    }

    @Override
    public Path pathFrom(Direction direction, Position position) {
        return pieceStrategy.pathFrom(direction, position);
    }

    @Override
    public boolean isColor(PieceColor color) {
        return this.color.equals(color);
    }

    @Override
    public boolean isPawn() {
        return pieceStrategy.isPawn();
    }

    @Override
    public boolean isKing() {
        return pieceStrategy.isKing();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
