package domain.state;

import domain.Board;
import domain.exception.AlreadyFinishedException;
import domain.exception.GameNotStartException;
import domain.piece.position.Position;

public class Finished extends Started {
    public Finished(Board board) {
        super(board);
    }

    @Override
    public boolean isEnd() {
        return true;
    }

    @Override
    public State run() {
        throw new GameNotStartException();
    }

    @Override
    public State move(Position start, Position end) {
        throw new GameNotStartException();
    }

    @Override
    public State finish() {
        throw new AlreadyFinishedException();
    }
}
