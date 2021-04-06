package domain.state;

import domain.exception.AlreadyFinishedException;
import domain.exception.GameNotStartException;
import domain.piece.objects.Piece;
import domain.piece.position.Position;

import java.util.Map;

public class Finished extends Started {
    public Finished(Map<Position, Piece> pieces) {
        super(pieces);
    }

    @Override
    public State run(Map<Position, Piece> pieces) {
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

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return true;
    }
}
