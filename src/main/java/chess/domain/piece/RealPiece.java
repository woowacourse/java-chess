package chess.domain.piece;

import chess.domain.board.Path;
import chess.domain.piece.strategy.Direction;
import chess.domain.piece.strategy.PieceStrategy;
import chess.domain.position.Position;
import chess.domain.result.Score;
import java.util.List;

public final class RealPiece implements Piece {

    private final Color color;
    private final PieceStrategy pieceStrategy;

    public RealPiece(final Color color, final PieceStrategy pieceStrategy) {
        this.color = color;
        this.pieceStrategy = pieceStrategy;
    }

    @Override
    public List<Direction> directions() {
        return pieceStrategy.directions();
    }

    @Override
    public Path pathFrom(final Direction direction, final Position position) {
        return pieceStrategy.pathFrom(direction, position);
    }

    @Override
    public boolean isDifferentColor(final Piece piece) {
        if (piece.isEmpty()) {
            return true;
        }
        final RealPiece realPiece = (RealPiece) piece;
        return !this.color.equals(realPiece.color);
    }

    @Override
    public boolean isSameColor(final Piece piece) {
        if (piece.isEmpty()) {
            return false;
        }
        final RealPiece realPiece = (RealPiece) piece;
        return this.color.equals(realPiece.color);
    }

    @Override
    public boolean isColor(final Color color) {
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

    @Override
    public String getName() {
        final String pieceName = pieceStrategy.name();
        if (color.equals(Color.BLACK)) {
            return pieceName.toUpperCase();
        }
        return pieceName;
    }

    @Override
    public Score score() {
        return new Score(pieceStrategy.value());
    }

    @Override
    public String type() {
        return pieceStrategy.type();
    }

    @Override
    public String colorName() {
        return color.getName();
    }
}
