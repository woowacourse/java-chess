package chess.domain.board;

import chess.domain.piece.EmptyPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceColor;
import chess.domain.position.Column;
import chess.domain.position.Position;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class Board {

    private final Map<Position, Piece> coordinates;

    private Board(Map<Position, Piece> coordinates) {
        this.coordinates = coordinates;
    }

    // TODO
    public static Board ofEmpty() {
        return new Board(new HashMap<>());
    }

    public Board movePiece(final Position sourcePosition, final Position targetPosition) {
        final Map<Position, Piece> nextCoordinates = new HashMap<>(coordinates);
        final Piece sourcePiece = coordinates.get(sourcePosition);
        final Piece targetPiece = coordinates.get(targetPosition);
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

    public boolean hasPieceColor(final Position sourcePosition, final PieceColor color) {
        return coordinates.get(sourcePosition).isColor(color);
    }

    public int kingCount() {
        return (int) coordinates.values()
                .stream()
                .filter(Piece::isKing)
                .count();
    }

    public int pawnCount(final Column column, final PieceColor color) {
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
                .map(path -> path.removeObstacleInPath(piece, this))
                .flatMap(List::stream)
                .collect(Collectors.toList())
        );
    }
}
