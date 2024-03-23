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

    public Map<Direction, Deque<Position>> calculateAllDirectionPositions(Position currentPosition) {
        return pieceType.calculateAllDirectionPositions(currentPosition);
    }

    public boolean isSameTeam(Piece piece) {
        return this.color == piece.color;
    }

    public boolean isSameTeam(Color color) {
        return this.color == color;
    }

    public boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public boolean isForward(Direction direction) {
        if (pieceType == PieceType.BLACK_PAWN) {
            return direction == Direction.S;
        }
        if (pieceType == PieceType.WHITE_PAWN) {
            return direction == Direction.N;
        }
        return true;
    }

    public boolean isAttack(Direction direction) {
        if (pieceType == PieceType.BLACK_PAWN) {
            return direction == Direction.SW || direction == Direction.SE;
        }
        if (pieceType == PieceType.WHITE_PAWN) {
            return direction == Direction.NE || direction == Direction.NW;
        }
        return true;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
