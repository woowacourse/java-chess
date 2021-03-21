package chess.domain.piece.black;

import chess.domain.piece.Position;
import chess.domain.piece.condition.KnightMoveCondition;
import chess.domain.piece.condition.MoveCondition;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BlackKnight extends BlackPiece {
    private static final String NOTATION = "N";

    public BlackKnight(Position position, List<MoveCondition> moveConditions) {
        super(position, moveConditions);
    }

    public static BlackKnight createWithCoordinate(int row, int column) {
        return new BlackKnight(
                new Position(row, column),
                Collections.singletonList(new KnightMoveCondition())
        );
    }

    @Override
    public double getScore() {
        return 2.5;
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
        return o instanceof BlackKnight && isSamePosition(((BlackKnight) o).getPosition());
    }

    @Override
    protected int toHashCode() {
        return Objects.hash(NOTATION, "BLACK", getPosition().hashCode());
    }
}
