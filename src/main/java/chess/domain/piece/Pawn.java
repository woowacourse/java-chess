package chess.domain.piece;

import chess.domain.location.Position;
import chess.domain.moveStrategy.MoveStrategy;
import chess.domain.moveStrategy.PawnDoubleMove;
import chess.domain.moveStrategy.PawnKillMove;
import chess.domain.moveStrategy.PawnSingleMove;

import java.util.List;

public class Pawn extends Division {

    public static final int PAWN_SCORE = 1;
    private final MoveStrategy pawnDoubleMove = new PawnDoubleMove(color);

    public Pawn(Color color) {
        super(color, "p", new PawnSingleMove(color), new PawnKillMove(color));
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public double score() {
        return PAWN_SCORE;
    }

    @Override
    public List<List<Position>> movablePositions(Position from) {
        if (from.hasRow(color.initPawnRow())) {
            return pawnDoubleMove.movablePositions(from);
        }
        return super.movablePositions(from);
    }

    @Override
    public List<List<Position>> killablePositions(Position from) {
        return super.killablePositions(from);
    }
}
