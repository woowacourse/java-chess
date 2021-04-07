package chess.domain.piece.white;

import chess.domain.piece.Position;
import chess.domain.piece.condition.MoveCondition;
import chess.domain.piece.condition.QueenMoveCondition;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class WhiteQueen extends WhitePiece {
    private static final String NOTATION = "q";

    public WhiteQueen(Position position, List<MoveCondition> moveConditions) {
        super(position, moveConditions);
    }

    public static WhiteQueen createWithCoordinate(int row, int column) {
        return new WhiteQueen(
                new Position(row, column),
                Collections.singletonList(
                        new QueenMoveCondition()
                )
        );
    }

    @Override
    public double getScore() {
        return 9;
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
        return o instanceof WhiteQueen && isSamePosition(((WhiteQueen) o).getPosition());
    }

    @Override
    protected int toHashCode() {
        return Objects.hash(NOTATION, "WHITE", getPosition().hashCode());
    }

}
