package chess.domain.piece.state.started;

import java.util.List;

import chess.domain.game.state.ChessBoard;
import chess.domain.game.state.position.Direction;
import chess.domain.game.state.position.Position;
import chess.domain.piece.state.PieceState;

public class StartedQueen extends Started{

    private final Continuous continuous = new Continuous();

    @Override
    public List<Position> findMovablePositions(Position source, ChessBoard board) {
        return continuous.findMovablePositions(Direction.all(), source, board);
    }

    @Override
    public PieceState updateState() {
        return new StartedQueen();
    }
}
