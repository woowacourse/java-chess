package chess.domain.piece;

import java.util.List;
import java.util.function.Predicate;

import chess.domain.Position;
import chess.domain.piece.constant.Direction;
import chess.domain.piece.constant.PieceDirections;
import chess.domain.piece.constant.PieceName;
import chess.domain.piece.constant.PieceScore;

public abstract class AbstractPiece implements Piece {

    private final PieceName pieceName;
    private final PieceScore pieceScore;
    private final PieceDirections pieceDirections;

    protected AbstractPiece(final PieceName pieceName,
                            final PieceScore pieceScore,
                            final PieceDirections pieceDirections) {
        this.pieceName = pieceName;
        this.pieceScore = pieceScore;
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
        return pieceName.getName();
    }

    @Override
    public final double getPieceScore() {
        return pieceScore.getScore();
    }
}
