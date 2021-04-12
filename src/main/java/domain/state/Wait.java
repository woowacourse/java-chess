package domain.state;

import domain.Board;
import domain.exception.GameNotStartException;
import domain.piece.position.Position;
import domain.score.Score;

public class Wait extends Started {
    public Wait(Board board) {
        super(board);
    }

    @Override
    public State run() {
        return new Running(board);
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
