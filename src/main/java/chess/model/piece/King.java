package chess.model.piece;

import chess.model.Team;
import chess.model.direction.Direction;
import chess.model.direction.strategy.MoveStrategy;
import chess.model.direction.strategy.SingleMove;
import chess.model.position.Position;
import java.util.List;
import java.util.Map;

public class King extends Piece {

    private static final double SCORE = 0;

    private final String symbol = "king";
    private final MoveStrategy moveStrategy;

    public King(final Team team) {
        super(team);
        this.moveStrategy = new SingleMove(team, Direction.ordinalDirection());
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean canMove(Position source, Position target, Map<Position, Piece> board) {
        List<Position> positions = moveStrategy.searchMovablePositions(source, board);
        return positions.contains(target);
    }

    @Override
    public boolean isKing() {
        return true;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

    @Override
    public double score() {
        return SCORE;
    }
}
