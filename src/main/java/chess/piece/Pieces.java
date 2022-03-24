package chess.piece;

import chess.position.Position;
import java.util.*;

public class Pieces {

    private List<Piece> pieces = new ArrayList<>();

    public Pieces(Piece... pieces) {
        this.pieces.addAll(List.of(pieces));
    }

    public Pieces(List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public void move(Position from, Position to) {
        if (isSameColorPiecesByPosition(from, to)) {
            throw new IllegalArgumentException(String.format(
                "%s에서 %s로 기물을 이동할 수 없습니다.", from, to));
        }

        if (HasObstacleBetweenPositions(from, to)) {
            Piece piece = findPieceByPosition(from);
            throw new IllegalArgumentException(String.format(
                "%s의 기물을 %s에서 %s로 이동할 수 없습니다.", piece.getClass().getSimpleName(), from, to));
        }

        Piece piece = findPieceByPosition(from);

        if (piece.isPawn() && hasPieceByPosition(to)) {
            throw new IllegalArgumentException(String.format("폰이 이동하려는 위치 %s에 기물이 있습니다.", to));
        }

        if (hasPieceByPosition(to)) {
            Piece toPiece = findPieceByPosition(to);
            piece.move(to);
            pieces.remove(toPiece);
        } else {
            piece.move(to);
        }
    }

    private boolean HasObstacleBetweenPositions(Position from, Position to) {
        return from.getPath(to).stream()
            .anyMatch(this::hasPieceByPosition);
    }

    private boolean isSameColorPiecesByPosition(Position from, Position to) {
        if (hasPieceByPosition(to)) {
            Piece fromPiece = findPieceByPosition(from);
            Piece toPiece = findPieceByPosition(to);
            return fromPiece.isSameColor(toPiece.getColor());
        }
        return false;
    }

    private Piece findPieceByPosition(Position position) {
        return pieces.stream()
            .filter(piece -> piece.isSamePosition(position))
            .findFirst()
            .orElseThrow(
                () -> new IllegalArgumentException(String.format("%s에는 기물이 없습니다.", position)));
    }

    private boolean hasPieceByPosition(Position position) {
        return pieces.stream()
            .anyMatch(piece -> piece.isSamePosition(position));
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public boolean isSameColorByPosition(Position position, Color color) {
        return findPieceByPosition(position).isSameColor(color);
    }
}
