package chess.domain.piece.state.started;

import java.util.List;

import chess.domain.game.state.ChessBoard;
import chess.domain.piece.position.Direction;
import chess.domain.piece.position.Position;
import chess.domain.piece.state.PieceState;

public class StartedRook extends Started{

    private final Continuous continuous = new Continuous();

    @Override
    public List<Position> getMovablePositions(Position source, ChessBoard board) {
        return continuous.getMovablePositions(Direction.upDownLeftRight(), source, board);
    }

    @Override
    public PieceState updateState() {
        return new StartedRook();
    }
}
