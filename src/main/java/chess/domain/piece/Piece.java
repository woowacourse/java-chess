package chess.domain.piece;

import chess.domain.board.position.Direction;
import chess.domain.board.position.Position;
import java.util.Map;
import java.util.Queue;

public class Piece {

    private final PieceType pieceType;
    private final Color color;

    public Piece(PieceType pieceType, Color color) {
        this.pieceType = pieceType;
        this.color = color;
    }

    public Map<Direction, Queue<Position>> generateAllDirectionPositions(Position currentPosition) {
        return pieceType.generateAllDirectionPositions(currentPosition);
    }

    public boolean isEnemy(Piece piece) {
        return this.color != piece.color;
    }

    public boolean isBlack() {
        return this.color == Color.BLACK;
    }

    public boolean isPawnAttackPossible(Direction direction) {
        if (pieceType == PieceType.BLACK_PAWN) {
            return direction == Direction.SW || direction == Direction.SE;
        }
        if (pieceType == PieceType.WHITE_PAWN) {
            return direction == Direction.NW || direction == Direction.NE;
        }
        return true;
    }

    public boolean isPawnMovePossible(Direction direction) {
        if (pieceType == PieceType.BLACK_PAWN) {
            return direction == Direction.S;
        }
        if (pieceType == PieceType.WHITE_PAWN) {
            return direction == Direction.N;
        }
        return true;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
