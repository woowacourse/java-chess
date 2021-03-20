package chess.domain.gamestate;

import chess.domain.Board;
import chess.domain.Score;
import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.InvalidCommandException;

public class Running extends GameState {

    public Running(Board board) {
        super(board);
    }

    @Override
    public GameState start() {
        throw new InvalidCommandException();
    }

    @Override
    public GameState gameSet() {
        return new GameSet(board());
    }

    @Override
    public GameState move(Position from, Position to, Side side) {
        board().move(from, to, side);
        if (board().isGameSet()) {
            return new GameSet(board());
        }
        return new Running(board());
    }

    @Override
    public GameState status() {
        return this;
    }

    @Override
    public boolean isGameSet() {
        return false;
    }

    @Override
    public Score score() {
        return Score.calculate(board());
    }
}
