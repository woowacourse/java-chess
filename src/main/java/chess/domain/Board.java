package chess.domain;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.PieceType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> positionToPiece;

    public Board(final List<Piece> pieces) {
        this.positionToPiece = init();
        pieces.forEach(piece -> positionToPiece.put(piece.getPosition(), piece));
    }

    private Map<Position, Piece> init() {
        Map<Position, Piece> positionToPiece = new HashMap<>();
        Arrays.stream(Column.values())
                .flatMap(column -> Arrays.stream(Row.values())
                        .map(row -> new Position(row, column)))
                .forEach(position -> positionToPiece.put(position, new Empty(position)));
        return positionToPiece;
    }

    public void move(Position departure, Position destination) {
        Piece targetPiece = positionToPiece.get(departure);
        Piece moved = targetPiece.move(this, destination);
        removePiece(departure);
        positionToPiece.put(destination, moved);
    }

    public void removePiece(Position position) {
        positionToPiece.put(position, new Empty(position));
    }

    public Map<Position, Piece> getPositionToPiece() {
        return this.positionToPiece;
    }

    public boolean isEmpty(final Position position) {
        return positionToPiece.get(position).isSameType(PieceType.EMPTY);
    }

    public boolean isExist(final Position position) {
        return !isEmpty(position);
    }

    public boolean isAlly(final Position position, final Color color) {
        return isExist(position) && positionToPiece.get(position).getColor() == color;
    }

    public boolean isEnemy(final Position position, final Color color) {
        return !isAlly(position, color);
    }
}
