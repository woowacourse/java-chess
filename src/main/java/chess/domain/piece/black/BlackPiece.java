package chess.domain.piece.black;

import chess.domain.piece.ChessPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.condition.MoveCondition;

import java.util.List;

public abstract class BlackPiece extends Piece {
    public static final String NOTATION = "흑";

    public BlackPiece(Position position, List<MoveCondition> moveConditions) {
        super(position, moveConditions);
    }

    @Override
    public boolean isSameColor(final ChessPiece piece) {
        if (this == piece) return true;
        if (piece instanceof BlackPiece) return true;
        return isSamePosition(piece.getPosition());
    }

    @Override
    public boolean equals(Object o) {
        return isSamePiece(o);
    }

    @Override
    public int hashCode() {
        return toHashCode();
    }

}
