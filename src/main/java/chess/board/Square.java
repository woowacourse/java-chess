package chess.board;

import chess.piece.Color;
import chess.piece.MovedPawn;
import chess.piece.Piece;
import chess.position.UnitDirection;
import chess.score.Score;

public class Square {

    private Piece piece;

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
        return getDestinationSquareAfterMovePieceTo(destination);
    }

    private Square getDestinationSquareAfterMovePieceTo(Square destination) {
        destination.piece = changePieceOnInitPawn(piece);
        piece = null;
        return destination;
    }

    public boolean canPieceMoveToward(UnitDirection direction, int step) {
        return hasPiece() && piece.isMovable(direction, step);
    }

    public boolean canPieceAttackToward(UnitDirection direction, int step) {
        return hasPiece() && piece.canAttack(direction, step);
    }

    private void validateSource(Square destination) {
        if (hasNoPiece()) {
            throw new IllegalArgumentException("출발 칸에 기물이 없습니다.");
        }
        if (hasSameColoredPieceWith(destination)) {
            throw new IllegalArgumentException("도착 칸에 자신의 기물이 있습니다.");
        }
    }

    private Piece changePieceOnInitPawn(Piece piece) {
        if (piece.isInitPawn()) {
            return new MovedPawn(piece.getColor());
        }
        return piece;
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
