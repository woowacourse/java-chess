package chess.domain.piece.state.started;

import java.util.ArrayList;
import java.util.List;

import chess.domain.ChessBoard;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;

public class MovedPawn extends StartedPawn {

    private final Direction forward;

    public MovedPawn(Direction forward) {
        super(forward);
        this.forward = forward;
    }

    @Override
    public List<Position> getMovablePositions(Position source, ChessBoard board) {
        List<Position> positions = new ArrayList<>();
        Position next = source.getNext(forward);

        if (board.canMoveOneStep(source, forward)) {
            positions.add(next);
        }

        positions.addAll(getKillablePositions(source, board));
        return positions;
    }
}
