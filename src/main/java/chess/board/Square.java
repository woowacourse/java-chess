package chess.board;

import chess.piece.Color;
import chess.piece.Piece;
import chess.position.UnitMovement;
import chess.score.Score;

public class Square {

    private final Piece piece;

    public Square(Piece piece) {
        this.piece = piece;
    }

    public static Square empty() {
        return new Square(null);
    }

    public boolean hasPiece() {
        return piece != null;
    }

    public boolean hasNoPiece() {
        return piece == null;
    }

    public Square movePieceTo(Square destination) {
        validateSource(destination);
        return new Square(piece.move());
    }

    public boolean canPieceMoveToward(UnitMovement movement, int step) {
        return hasPiece() && piece.isMovable(movement, step);
    }

    public boolean canPieceAttackToward(UnitMovement movement, int step) {
        return hasPiece() && piece.canAttack(movement, step);
    }

    private void validateSource(Square destination) {
        if (hasNoPiece()) {
            throw new IllegalArgumentException("출발 칸에 기물이 없습니다.");
        }
        if (hasSameColoredPieceWith(destination)) {
            throw new IllegalArgumentException("도착 칸에 자신의 기물이 있습니다.");
        }
    }

    private boolean hasSameColoredPieceWith(Square other) {
        if (hasNoPiece() || other.hasNoPiece()) {
            return false;
        }
        return piece.hasSameColorWith(other.piece);
    }

    public boolean hasPieceColored(Color color) {
        return hasPiece() && piece.hasColorOf(color);
    }

    public boolean hasPieceOpponentColored(Color color) {
        return hasPiece() && piece.hasOpponentColorOf(color);
    }

    public boolean hasOpponentPieceOn(Square other) {
        if (hasNoPiece() || other.hasNoPiece()) {
            return false;
        }
        return piece.hasDifferentColorWith(other.piece);
    }

    public boolean hasKing() {
        return hasPiece() && piece.isKing();
    }

    public boolean hasPawn() {
        return hasPiece() && piece.isPawn();
    }

    public boolean hasNoPawn() {
        return hasNoPiece() || !piece.isPawn();
    }

    public Score getScore() {
        if (hasNoPiece()) {
            return Score.ZERO;
        }
        return piece.getScore();
    }

    public Piece getPiece() {
        return piece;
    }
}
