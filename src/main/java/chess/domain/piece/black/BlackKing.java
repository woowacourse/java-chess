package chess.domain.piece.black;

import chess.domain.piece.Position;
import chess.domain.piece.condition.KingMoveCondition;
import chess.domain.piece.condition.MoveCondition;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class BlackKing extends BlackPiece {
    private static final String NOTATION = "K";

    public BlackKing(Position position, List<MoveCondition> moveConditions) {
        super(position, moveConditions);
    }

    public static BlackKing createWithCoordinate(int row, int column) {
        return new BlackKing(
                new Position(row, column),
                Collections.singletonList(new KingMoveCondition())
        );
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public double getScore() {
        return 0;
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
        return o instanceof BlackKing && isSamePosition(((BlackKing) o).getPosition());
    }

    @Override
    protected int toHashCode() {
        return Objects.hash(NOTATION, "BLACK", getPosition().hashCode());
    }

}
