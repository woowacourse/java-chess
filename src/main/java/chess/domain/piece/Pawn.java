package chess.domain.piece;

import chess.domain.moveStrategy.MoveStrategy;
import chess.domain.moveStrategy.PawnDoubleMove;
import chess.domain.moveStrategy.PawnKillMove;
import chess.domain.moveStrategy.PawnSingleMove;
import chess.domain.location.Position;

import java.util.List;

public class Pawn extends Division {

    public static final int PAWN_SCORE = 1;
    private final MoveStrategy pawnSingleMove = new PawnSingleMove(color);
    private final MoveStrategy pawnDoubleMove = new PawnDoubleMove(color);
    private final MoveStrategy pawnKillMove = new PawnKillMove(color);

    public Pawn(Color color) {
        super(color, "p");
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public double score() {
        return PAWN_SCORE;
    }

    @Override
    public boolean isPawn() {
        return true;
    }

    @Override
    public List<List<Position>> movablePositions(Position from) {
        if (from.hasRow(color.initPawnRow())) {
            return pawnDoubleMove.movablePositions(from);
        }
        return pawnSingleMove.movablePositions(from);
    }

    @Override
    public List<List<Position>> killablePositions(Position from) {
        return pawnKillMove.movablePositions(from);
    }
}
