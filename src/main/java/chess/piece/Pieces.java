package chess.piece;

import chess.position.Position;
import java.util.List;

public class Pieces {

    private final List<Piece> pieces;

    public Pieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public boolean hasPieceByPosition(Position position) {
        return pieces.stream()
            .anyMatch(piece -> piece.isSamePosition(position));
    }

    public Piece findPieceByPosition(Position position) {
        return pieces.stream().filter(piece -> piece.isSamePosition(position))
            .findFirst().orElseThrow(() -> new IllegalArgumentException(String.format("%s에 기물이 없습니다.", position)));
    }

    public boolean hasObstacleOnLinearPath(Position from, Position to) {
        return from.getLinearPath(to).stream()
            .anyMatch(this::hasPieceByPosition);
    }

    public boolean hasSameColorPieceByPosition(Position to, Color color) {
        if (!hasPieceByPosition(to)) {
            return false;
        }
        Piece targetPiece = findPieceByPosition(to);
        return targetPiece.isSameColor(color);
    }

}
