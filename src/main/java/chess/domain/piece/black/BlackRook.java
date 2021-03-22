package chess.domain.piece.black;

import chess.domain.piece.Position;
import chess.domain.piece.condition.MoveCondition;
import chess.domain.piece.condition.RookMoveCondition;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BlackRook extends BlackPiece {
    private static final String NOTATION = "R";

    public BlackRook(Position position, List<MoveCondition> moveConditions) {
        super(position, moveConditions);
    }

    public static BlackRook createWithCoordinate(int row, int column) {
        return new BlackRook(
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
        return o instanceof BlackRook && isSamePosition(((BlackRook) o).getPosition());
    }

    @Override
    protected int toHashCode() {
        return Objects.hash(NOTATION, "BLACK", getPosition().hashCode());
    }

}
