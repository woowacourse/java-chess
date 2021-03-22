package chess.domain.piece.white;

import chess.domain.piece.ChessPiece;
import chess.domain.piece.Piece;
import chess.domain.piece.Position;
import chess.domain.piece.condition.MoveCondition;

import java.util.List;

public abstract class WhitePiece extends Piece {
    public static final String NOTATION = "백";

    public WhitePiece(Position position, List<MoveCondition> moveConditions) {
        super(position, moveConditions);
    }

    @Override
    public boolean isSameColor(ChessPiece piece) {
        if (this == piece) return true;
        if (piece instanceof WhitePiece) return true;
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

