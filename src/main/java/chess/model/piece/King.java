package chess.model.piece;

import chess.model.Team;
import chess.model.direction.Direction;
import chess.model.direction.strategy.MoveStrategy;
import chess.model.direction.strategy.SingleMove;
import chess.model.position.Position;
import java.util.List;
import java.util.Map;

public class King extends Piece {

    private final MoveStrategy moveStrategy;

    public King(final Team team) {
        super(team);
        this.moveStrategy = new SingleMove(team, Direction.ordinalDirection());
    }

    @Override
    public String getSymbol() {
        return "KING";
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
        return 0;
    }
}
