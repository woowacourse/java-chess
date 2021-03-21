package chess.domain.piece.white;

import chess.domain.piece.Position;
import chess.domain.piece.condition.KnightMoveCondition;
import chess.domain.piece.condition.MoveCondition;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WhiteKnight extends WhitePiece {
    private static final String NOTATION = "n";

    public WhiteKnight(Position position, List<MoveCondition> moveConditions) {
        super(position, moveConditions);
    }

    public static WhiteKnight createWithCoordinate(int row, int column) {
        return new WhiteKnight(
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
        return o instanceof WhiteKnight && isSamePosition(((WhiteKnight) o).getPosition());
    }

    @Override
    protected int toHashCode() {
        return Objects.hash(NOTATION, "WHITE", getPosition().hashCode());
    }

}
