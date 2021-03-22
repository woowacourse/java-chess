package chess.domain.piece.white;

import chess.domain.piece.Position;
import chess.domain.piece.condition.CatchingPieceWhitePawnMoveCondition;
import chess.domain.piece.condition.FirstTurnWhitePawnMoveCondition;
import chess.domain.piece.condition.MoveCondition;
import chess.domain.piece.condition.NormalWhitePawnMoveCondition;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WhitePawn extends WhitePiece {
    private static final String NOTATION = "p";

    public WhitePawn(Position position, List<MoveCondition> moveConditions) {
        super(position, moveConditions);
    }

    public static WhitePawn createWithCoordinate(int row, int column) {
        return new WhitePawn(
                new Position(row, column),
                Arrays.asList(
                        new FirstTurnWhitePawnMoveCondition(),
                        new NormalWhitePawnMoveCondition(),
                        new CatchingPieceWhitePawnMoveCondition()
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
        return o instanceof WhitePawn && isSamePosition(((WhitePawn) o).getPosition());
    }

    @Override
    protected int toHashCode() {
        return Objects.hash(NOTATION, "WHITE", getPosition().hashCode());
    }

}
