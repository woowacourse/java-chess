package chess.domain.piece.white;

import chess.domain.piece.Position;
import chess.domain.piece.condition.BishopMoveCondition;
import chess.domain.piece.condition.MoveCondition;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WhiteBishop extends WhitePiece {
    private static final String NOTATION = "b";

    public WhiteBishop(Position position, List<MoveCondition> moveConditions) {
        super(position, moveConditions);
    }

    public static WhiteBishop createWithCoordinate(int row, int column) {
        return new WhiteBishop(
                new Position(row, column),
                Collections.singletonList(new BishopMoveCondition())
        );
    }

    @Override
    public double getScore() {
        return 3;
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
        return o instanceof WhiteBishop && isSamePosition(((WhiteBishop) o).getPosition());
    }

    @Override
    protected int toHashCode() {
        return Objects.hash(NOTATION, "WHITE", getPosition().hashCode());
    }

}
