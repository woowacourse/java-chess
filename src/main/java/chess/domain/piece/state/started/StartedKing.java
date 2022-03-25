package chess.domain.piece.state.started;

import java.util.List;

import chess.domain.ChessBoard;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;
import chess.domain.piece.state.State;

public class StartedKing extends Started{

    private final NonContinuous nonContinuous = new NonContinuous();

    @Override
    public List<Position> getMovablePositions(Position source, ChessBoard board) {
        return nonContinuous.getMovablePositions(source, board, Direction.all());
    }

    @Override
    public State updateState() {
        return new StartedKing();
    }
}
