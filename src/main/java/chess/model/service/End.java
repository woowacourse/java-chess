package chess.model.service;

import chess.model.domain.board.Score;
import chess.model.domain.piece.Color;
import chess.model.domain.piece.Piece;
import chess.model.domain.position.Position;
import chess.model.exception.EndCantExecuteException;
import java.util.List;
import java.util.Map;

public class End implements State {

    private static final End INSTANCE = new End();

    private End() {
    }

    public static End getInstance() {
        return INSTANCE;
    }

    @Override
    public State start() {
        throw new EndCantExecuteException();
    }

    @Override
    public State move(final Position from, final Position to) {
        throw new EndCantExecuteException();
    }

    @Override
    public Map<Color, Score> status() {
        throw new EndCantExecuteException();
    }

    @Override
    public State end() {
        throw new EndCantExecuteException();
    }

    @Override
    public Map<Position, Piece> getBoard() {
        throw new EndCantExecuteException();
    }

    @Override
    public State load(final long id) {
        throw new EndCantExecuteException();
    }

    @Override
    public List<Long> findAllId() {
        throw new EndCantExecuteException();
    }
}
