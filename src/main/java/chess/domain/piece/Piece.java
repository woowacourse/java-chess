package chess.domain.piece;

import chess.domain.game.Color;
import chess.domain.position.Position;

import java.util.List;
import java.util.Map;

public class Piece {
    private final Color color;
    private final PieceType pieceType;
    private final MovingPattern movingPattern;
    private int moveCount;


    public Piece(Color color, PieceType pieceType, MovingPattern movingPattern, int moveCount) {
        this.color = color;
        this.pieceType = pieceType;
        this.movingPattern = movingPattern;
        this.moveCount = moveCount;
    }

    public String signature() {
        return color.correctSignature(pieceType.getSignature());
    }

    public boolean isSameColor(Color color) {
        return this.color == color;
    }

    public boolean isNeverDisplaced() {
        return moveCount == 0;
    }

    public Piece displaced() {
        this.moveCount++;
        return this;
    }

    public boolean isBlank() {
        return pieceType == PieceType.BLANK;
    }

    public boolean isPawn() {
        return pieceType == PieceType.PAWN;
    }

    public boolean isKing() {
        return pieceType == PieceType.KING;
    }

    public boolean isRook() {
        return pieceType == PieceType.ROOK;
    }

    public boolean isEnPassantAvailable() {
        return moveCount == 1;
    }

    public boolean isMovable(Map<Position, Piece> board, Position source, Position target) {
        return movingPattern.isMovable(board, source, target);
    }

    public List<Position> findRoute(Position source, Position target) {
        return movingPattern.findRoute(source, target);
    }

    public double score() {
        return pieceType.getScore();
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public Color getColor() {
        return color;
    }

    public int getMoveCount() {
        return moveCount;
    }
}
