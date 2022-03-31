package chess.domain.piece;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;
import chess.domain.piece.property.Color;
import java.util.ArrayList;
import java.util.List;

public class MovedPawn extends StartedPawn {

    private final Direction forward;

    public MovedPawn(Color color) {
        super(color);
        forward = color.getForwardDirection();
    }

    @Override
    public List<Position> getMovablePaths(Position source, ChessBoard board) {
        List<Position> movablePositions = new ArrayList<>();
        Position next = source.getNext(forward);

        if (board.canMoveOrKillByOneStep(source, forward)) {
            movablePositions.add(next);
        }

        movablePositions.addAll(getKillablePositions(source, board));
        return movablePositions;
    }
}
