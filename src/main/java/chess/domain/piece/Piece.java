package chess.domain.piece;

import java.util.Set;
import java.util.stream.Collectors;

import chess.domain.chessboard.Chessboard;
import chess.domain.chessboard.attribute.Square;
import chess.domain.piece.attribute.Color;
import chess.domain.piece.attribute.Position;

public abstract class Piece {

    private final Color color;
    private final Position position;

    public Piece(final Color color, final Position position) {
        this.color = color;
        this.position = position;
    }

    protected static <P extends Piece> Set<P> initialPiecesOf(
            final Set<Position> initialPositions,
            final Color color,
            final PieceConstructor<P> pieceConstructor
    ) {
        return initialPositions.stream()
                .map(position -> pieceConstructor.create(color, position))
                .collect(Collectors.toUnmodifiableSet());
    }

    protected static Set<Position> initialPositionsBy(
            final Color color,
            final Set<Position> whitePositions,
            final Set<Position> blackPositions
    ) {
        return (color.isBlack()) ? blackPositions : whitePositions;
    }

    protected static Position initialPositionBy(
            final Color color,
            final Position whitePosition,
            final Position blackPosition
    ) {
        return (color.isBlack()) ? blackPosition : whitePosition;
    }

    protected void validateTarget(final Set<Position> possiblePositions, final Position target) {
        if (!possiblePositions.contains(target)) {
            throw new IllegalArgumentException("이동할 수 없는 위치입니다: %s".formatted(target));
        }
    }

    protected boolean isAttackable(final Square square) {
        if (!square.isEmpty()) {
            Piece piece = square.piece();
            return color() != piece.color();
        }
        return false;
    }

    public Color color() {
        return color;
    }

    public Position position() {
        return position;
    }

    public abstract Piece move(final Chessboard chessboard, final Position target);

    public abstract Set<Position> movablePositions(final Chessboard chessboard);
}
