package chess.domain.piece.state.started;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import chess.domain.game.state.position.Direction;
import chess.domain.game.state.position.Position;
import chess.domain.piece.Piece;

public class MovedPawn extends StartedPawn {

    private final Direction forward;

    public MovedPawn(Direction forward) {
        super(forward);
        this.forward = forward;
    }

    @Override
    public List<Position> findMovablePositions(Position source, Map<Position, Piece> board) {
        List<Position> positions = new ArrayList<>();
        Position next = source.findNext(forward);

        if (canOnlyMoveByOneStep(board, source, forward)) {
            positions.add(next);
        }

        positions.addAll(getKillablePositions(source, board));
        return positions;
    }
}
