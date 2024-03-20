package chess.piece;

import chess.board.Position;

public abstract class Piece {

    private final int maxUnitMove;
    private final PieceAttributes pieceAttributes;

    public Piece(PieceType pieceType, Color color, int maxUnitMove) {
        this.pieceAttributes = new PieceAttributes(pieceType, color);
        this.maxUnitMove = maxUnitMove;
    }

    public abstract boolean isMovable(Position source, Position destination);

    public boolean hasMultipleUnitMoves() {
        return maxUnitMove > 1;
    }

    public boolean hasAttributesOf(PieceAttributes pieceAttributes) {
        return this.pieceAttributes.equals(pieceAttributes);
    }

    public boolean isAttackable(Position source, Position destination) {
        return isMovable(source, destination);
    }

    public boolean isAllyPiece(Piece piece) {
        return pieceAttributes.hasSameColorOf(piece.color());
    }

    public boolean isOpponentPiece(Piece piece) {
        return !isAllyPiece(piece);
    }

    public boolean hasColorOf(Color color) {
        return pieceAttributes.hasSameColorOf(color);
    }

    protected Color color() {
        return pieceAttributes.getColor();
    }
}
