package chess.domain.piece.white;

import chess.domain.piece.Position;
import chess.domain.piece.condition.MoveCondition;
import chess.domain.piece.condition.RookMoveCondition;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WhiteRook extends WhitePiece {
    private static final String NOTATION = "r";

    public WhiteRook(Position position, List<MoveCondition> moveConditions) {
        super(position, moveConditions);
    }

    public static WhiteRook createWithCoordinate(int row, int column) {
        return new WhiteRook(
                new Position(row, column),
                Collections.singletonList(new RookMoveCondition())
        );
    }

    @Override
    public double getScore() {
        return 5;
    }

    @Override
    public String getNotation() {
        return NOTATION;
    }

    @Override
    public boolean equals(Object o) {
        return isSamePiece(o);
    }

    @Override
    public int hashCode() {
        return toHashCode();
    }

    @Override
    protected boolean isSamePiece(Object o) {
        if (o == this) return true;
        return o instanceof WhiteRook && isSamePosition(((WhiteRook) o).getPosition());
    }

    @Override
    protected int toHashCode() {
        return Objects.hash(NOTATION, "WHITE", getPosition().hashCode());
    }

}
