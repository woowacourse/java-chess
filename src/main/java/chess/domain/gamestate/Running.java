package chess.domain.gamestate;

import chess.domain.Board;
import chess.domain.Score;
import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.InvalidCommandException;

public class Running extends GameState {

    public Running(Board board, Side side) {
        super(board, side);
    }

    @Override
    public GameState start() {
        throw new InvalidCommandException();
    }

    @Override
    public GameState move(Position from, Position to) {
        board().move(from, to, side());
        if (board().isGameSet()) {
            return new GameSet(board(), side());
        }
        return new Running(board(), changeTurn());
    }

    @Override
    public GameState status() {
        return this;
    }

    @Override
    public State finished() {
        return new Finished(board(), side());
    }

    @Override
    public boolean isGameSet() {
        return false;
    }

    @Override
    public Score score() {
        return Score.from(board());
    }

    @Override
    public Side winner() {
        throw new InvalidCommandException();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
