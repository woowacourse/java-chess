package chess.model.piece;

import chess.model.Team;
import chess.model.direction.strategy.PawnMove;
import chess.model.position.Position;
import java.util.List;
import java.util.Map;

public class Pawn extends Piece {

    private static final double SCORE = 1;

    private final String symbol = "pawn";
    private final PawnMove pawnMove;

    public Pawn(final Team team) {
        super(team);
        this.pawnMove = new PawnMove(team);
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        List<Position> positions = pawnMove.searchMovablePositions(source, board);
        return positions.contains(target);
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
        return SCORE;
    }
}
