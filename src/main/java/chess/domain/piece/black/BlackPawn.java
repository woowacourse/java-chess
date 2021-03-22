package chess.domain.piece.black;

import chess.domain.piece.Position;
import chess.domain.piece.condition.CatchingPieceBlackPawnMoveCondition;
import chess.domain.piece.condition.FirstTurnBlackPawnMoveCondition;
import chess.domain.piece.condition.MoveCondition;
import chess.domain.piece.condition.NormalBlackPawnMoveCondition;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BlackPawn extends BlackPiece {
    private static final String NOTATION = "P";

    public BlackPawn(Position position, List<MoveCondition> moveConditions) {
        super(position, moveConditions);
    }

    public static BlackPawn createWithCoordinate(int row, int column) {
        return new BlackPawn(
                new Position(row, column),
                Arrays.asList(
                        new FirstTurnBlackPawnMoveCondition(),
                        new NormalBlackPawnMoveCondition(),
                        new CatchingPieceBlackPawnMoveCondition()
                )
        );
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public double getScore() {
        return 1;
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
        return o instanceof BlackPawn && isSamePosition(((BlackPawn) o).getPosition());
    }

    @Override
    protected int toHashCode() {
        return Objects.hash(NOTATION, "BLACK", getPosition().hashCode());
    }

}
