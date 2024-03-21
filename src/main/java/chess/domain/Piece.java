package chess.domain;

import java.util.Deque;
import java.util.Map;

public class Piece {

    private final PieceType pieceType;
    private final Color color;

    public Piece(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public boolean isSameTeam(Piece piece) {
        return this.color == piece.color;
    }

    public boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public Map<Direction, Deque<Position>> calculateAllDirectionPositions(Position currentPosition) {
        return pieceType.calculateAllDirectionPositions(currentPosition);
    }
}
