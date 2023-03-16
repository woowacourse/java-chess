package chess.domain.piece;

import chess.domain.piece.moveRule.MoveRule;

import java.util.Objects;

public final class Piece {
    private final PieceState pieceState; //이동 규칙, color를 넘기면 출력할 이름 변환
    private final Color color;

    public Piece(MoveRule moveRule, Color color) {
        this.pieceState = new PieceState(moveRule);
        this.color = color;
    }

    public boolean isOpponent(Piece other) {
        return this.color != other.color;
    }

    public String formatName() {
        return pieceState.formatName(color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return Objects.equals(pieceState, piece.pieceState) && color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceState, color);
    }

    @Override
    public String toString() {
        return "Piece{" +
                "pieceState=" + pieceState +
                ", color=" + color +
                '}';
    }
}
