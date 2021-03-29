package chess.domain.board;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.Color;
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
        final Piece sourcePiece = coordinates.get(sourcePosition);
        nextCoordinates.replace(targetPosition, sourcePiece);
        nextCoordinates.replace(sourcePosition, EmptyPiece.getInstance());
        return new Board(nextCoordinates);
    }

    public boolean hasAvailablePath(final Position sourcePosition, final Position targetPosition) {
        return pathsOf(sourcePosition).contains(targetPosition);
    }

    public List<Piece> remainingPieces() {
        return coordinates.values()
                .stream()
                .filter(piece -> !piece.isEmpty())
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

    public int pawnCount(final Column column, final Color color) {
        return (int) coordinates.entrySet()
                .stream()
                .filter(entry -> entry.getKey().isOfColumn(column))
                .filter(entry -> entry.getValue().isPawn())
                .filter(entry -> entry.getValue().isColor(color))
                .count()
                ;
    }

    private Path pathsOf(final Position sourcePosition) {
        final Piece piece = coordinates.get(sourcePosition);
        return new Path(
                piece.directions()
                .stream()
                .map(direction -> piece.pathFrom(direction, sourcePosition))
                .map(path -> path.removeObstacleInPath(sourcePosition, this))
                .flatMap(List::stream)
                .collect(Collectors.toList()));
    }
}
