package chess.domain.piece;

import chess.domain.board.Position;
import chess.domain.piece.strategy.MoveDirection;

import java.util.Objects;

public class Piece {

    public static final String OVER_DISTANCE_MESSAGE = "해당 말의 이동 가능한 거리를 초과했습니다.";
    public static final String UNABLE_CROSS_MESSAGE = "해당 말은 뛰어넘기가 불가합니다.";
    public static final String UNABLE_MOVE_TYPE_MESSAGE = "해당 말이 이동할 수 있는 위치가 아닙니다.";
    public static final String SAME_TEAM_MESSAGE = "같은 팀의 말입니다.";

    private PieceKind pieceKind;
    private PieceColor pieceColor;

    public Piece(PieceKind pieceKind, PieceColor pieceColor) {
        this.pieceKind = pieceKind;
        this.pieceColor = pieceColor;
    }

    public boolean isSameColor(Piece piece) {
        return isSameColor(piece.pieceColor);
    }

    public boolean isSameColor(PieceColor anotherPieceColor) {
        return pieceColor.isSameColor(anotherPieceColor);
    }

    public boolean isMovingForward(Position source, Position target) {
        MoveDirection moveDirection = MoveDirection.getDirection(source, target);
        if (pieceColor.isSameColor(PieceColor.WHITE)) {
            return MoveDirection.isWhiteForward(moveDirection);
        }

        return MoveDirection.isBlackForward(moveDirection);
    }

    public boolean isPawn() {
        return pieceKind.isSameKind(PieceKind.PAWN);
    }

    public boolean isKing() {
        return pieceKind.isSameKind(PieceKind.KING);
    }

    public void movable(Position source, Position target) {
        pieceKind.movable(source, target);
    }

    public PieceKind kind() {
        return pieceKind;
    }

    public String symbol() {
        return pieceKind.getName(pieceColor);
    }

    public PieceColor color() {
        return this.pieceColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return pieceKind == piece.pieceKind && pieceColor == piece.pieceColor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceKind, pieceColor);
    }
}
