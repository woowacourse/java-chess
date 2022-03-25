package chess.domain.piece.state;

import java.util.List;

import chess.domain.ChessBoard;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;

public class StartedBishop extends Started{

    private final Continuous continuous = new Continuous();

    @Override
    public List<Position> getMovablePositions(Position source, ChessBoard board) {
        return continuous.getMovablePositions(Direction.diagonal(), source, board);
    }

    @Override
    public State updateState() {
        return new StartedBishop();
    }
}
