package chess.domain.gamestate;

import chess.domain.Score;
import chess.domain.Side;
import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.exception.InvalidCommandException;

public class Finished extends GameState {
    public Finished(Board board, Side side) {
        super(board, side);
    }

    @Override
    public State start() {
        throw new InvalidCommandException();
    }

    @Override
    public State move(Position from, Position to) {
        throw new InvalidCommandException();
    }

    @Override
    public State status() {
        throw new InvalidCommandException();
    }

    @Override
    public State finished() {
        throw new InvalidCommandException();
    }

    @Override
    public boolean isGameSet() {
        return false;
    }

    @Override
    public Score score() {
        throw new InvalidCommandException();
    }

    @Override
    public Side winner() {
        throw new InvalidCommandException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
