package chess.domain.piece;

import chess.domain.piece.moveRule.MoveRule;

import java.util.Objects;

public class PieceState {
    private final MoveRule moveRule;
    private final PieceType pieceType;

    public PieceState(MoveRule moveRule) {
        this.moveRule = moveRule;
        this.pieceType = moveRule.pieceType();
    }

    public String formatName(Color color) {
        return pieceType.formatName(color);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PieceState that = (PieceState) o;
        return pieceType == that.pieceType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType);
    }
}
