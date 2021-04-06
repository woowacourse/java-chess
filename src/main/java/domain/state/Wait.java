package domain.state;

import domain.exception.GameNotStartException;
import domain.piece.objects.Piece;
import domain.piece.position.Position;
import domain.score.Score;

import java.util.Map;

public class Wait extends Started {
    public Wait(Map<Position, Piece> pieces) {
        super(pieces);
    }

    @Override
    public State run(Map<Position, Piece> pieces) {
        return new Running(pieces);
    }

    @Override
    public State move(Position start, Position end) {
        throw new GameNotStartException();
    }

    @Override
    public State finish() {
        throw new GameNotStartException();
    }

    @Override
    public Score blackScore() {
        throw new GameNotStartException();
    }

    @Override
    public Score whiteScore() {
        throw new GameNotStartException();
    }
}
