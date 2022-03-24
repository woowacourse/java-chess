package chess.domain.piece.state;

import java.util.ArrayList;
import java.util.List;

import chess.domain.ChessBoard;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;

public class StartedBishop implements State{

    private final Continuous continuous = new Continuous();

    @Override
    public List<Position> getMovablePositions(Position source, ChessBoard board) {
        List<Position> movablePositions = new ArrayList<>();
        for (Direction direction : Direction.bishop()) {
            movablePositions.addAll(continuous.getPositions(source, board, direction));
        }

        return movablePositions;
    }

    @Override
    public State killed() {
        return new Dead();
    }

    @Override
    public State updateState() {
        return new StartedBishop();
    }
}
