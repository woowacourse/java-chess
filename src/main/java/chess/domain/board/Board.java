package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.RealPiece;
import chess.domain.piece.strategy.MovedBlackPawnStrategy;
import chess.domain.piece.strategy.MovedWhitePawnStrategy;
import chess.domain.position.Column;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Board {

    private final Map<Position, Piece> coordinates;

    public Board(Map<Position, Piece> coordinates) {
        this.coordinates = coordinates;
    }

    public Board movePiece(final Position sourcePosition, final Position targetPosition) {
        final Map<Position, Piece> nextCoordinates = new HashMap<>(coordinates);
        final Piece sourcePiece = changeStrategyIfNeeded(coordinates.get(sourcePosition));
        nextCoordinates.replace(targetPosition, sourcePiece);
        nextCoordinates.replace(sourcePosition, EmptyPiece.getInstance());
        return new Board(nextCoordinates);
    }

    private Piece changeStrategyIfNeeded(Piece piece) {
        if (piece.isPawn()) {
            return movedPawn(piece);
        }
        return piece;
    }

    private Piece movedPawn(Piece piece) {
        if (piece.isColor(Color.WHITE)) {
            return new RealPiece(Color.WHITE, new MovedWhitePawnStrategy());
        }
        return new RealPiece(Color.BLACK, new MovedBlackPawnStrategy());
    }

    public boolean hasAvailablePath(final Position sourcePosition, final Position targetPosition) {
        return pathsOf(sourcePosition).contains(targetPosition);
    }

    public List<Piece> remainingSpecialPieces(final Color color) {
        return coordinates.values()
                .stream()
                .filter(piece -> !piece.isEmpty())
                .filter(piece -> !piece.isPawn())
                .filter(piece -> piece.isColor(color))
                .collect(Collectors.toList())
                ;
    }

    public Piece pieceAt(final Position position) {
        return coordinates.get(position);
    }

    public int kingCount() {
        return (int) coordinates.values()
                .stream()
                .filter(Piece::isKing)
                .count()
                ;
    }

    public Piece remainingKing() {
        return coordinates.values()
                .stream()
                .filter(Piece::isKing)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("킹이 없습니다."))
                ;
    }

    public int pawnCount(final Column column, final Color color) {
        return (int) coordinates.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isOfColumn(column))
                .filter(entry -> entry.getValue().isPawn())
                .filter(entry -> entry.getValue().isColor(color))
                .count()
                ;
    }

    public Path pathsOf(final Position sourcePosition) {
        final Piece piece = coordinates.get(sourcePosition);
        final List<Position> positions = piece.directions()
                .stream()
                .map(direction -> piece.pathFrom(direction, sourcePosition))
                .map(path -> path.removeObstacleInPath(sourcePosition, this))
                .flatMap(List::stream)
                .collect(Collectors.toList());
        return new Path(positions);
    }

    public Map<Position, Piece> coordinates() {
        return coordinates;
    }
}
