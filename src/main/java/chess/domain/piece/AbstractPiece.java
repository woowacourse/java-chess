package chess.domain.piece;

import java.util.List;
import java.util.function.Predicate;

import chess.domain.Position;
import chess.domain.piece.constant.Direction;
import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceType;

public abstract class AbstractPiece implements Piece {

    private final PieceType pieceType;
    private final PieceDirections pieceDirections;

    protected AbstractPiece(final PieceType pieceType, final PieceDirections pieceDirections) {
        this.pieceType = pieceType;
        this.pieceDirections = pieceDirections;
    }

    @Override
    public final List<Position> calculateRouteToMove(final Position source, final Position target) {
        return calculateRoute(pieceDirections.getDirectionsToMove(), source, target, this::isRouteSizeEnoughToMove);
    }

    @Override
    public final List<Position> calculateRouteToAttack(final Position source, final Position target) {
        return calculateRoute(pieceDirections.getDirectionsToAttack(), source, target, this::isRouteSizeEnoughToAttack);
    }

    private List<Position> calculateRoute(final List<Direction> directions,
                                          final Position source,
                                          final Position target,
                                          final Predicate<Integer> sizeCheckPredicate) {
        return directions.stream()
                .map(direction -> direction.calculateUnidirectionalRoute(source, target))
                .filter(route -> !route.isEmpty())
                .filter(route -> sizeCheckPredicate.test(route.size()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("이동 가능한 경로가 없습니다."));
    }

    protected abstract boolean isRouteSizeEnoughToMove(final int routeSize);

    protected abstract boolean isRouteSizeEnoughToAttack(final int routeSize);

    @Override
    public final String getPieceName() {
        return pieceType.getPieceName();
    }

    @Override
    public final double getPieceScore() {
        return pieceType.getPieceScore();
    }

    @Override
    public String toString() {
        return "Piece{" + pieceType.getPieceName() + '}';
    }
}
