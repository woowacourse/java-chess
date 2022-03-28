package chess.domain.piece.state.started;

import java.util.List;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;
import chess.domain.piece.state.PieceState;

public class StartedKing extends Started{

    private final NonContinuous nonContinuous = new NonContinuous();

    @Override
    public List<Position> findMovablePositions(Position source, ChessBoard board) {
        return nonContinuous.findMovablePositions(source, board, Direction.all());
    }

    @Override
    public PieceState updateState() {
        return new StartedKing();
    }
}
