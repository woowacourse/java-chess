package chess.domain.piece.black;

import chess.domain.piece.Position;
import chess.domain.piece.condition.BishopMoveCondition;
import chess.domain.piece.condition.MoveCondition;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BlackBishop extends BlackPiece {
    public static final String NOTATION = "B";

    public BlackBishop(Position position, List<MoveCondition> moveConditions) {
        super(position, moveConditions);
    }

    public static BlackBishop createWithCoordinate(int row, int column) {
        return new BlackBishop(
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
        return o instanceof BlackBishop && isSamePosition(((BlackBishop) o).getPosition());
    }

    @Override
    protected int toHashCode() {
        return Objects.hash(NOTATION, "BLACK", getPosition().hashCode());
    }

}
