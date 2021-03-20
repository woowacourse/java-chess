package chess.domain.gamestate;

import chess.domain.Board;
import chess.domain.Score;
import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.InvalidCommandException;

public class GameSet extends GameState {

    public GameSet(Board board) {
        super(board);
    }

    @Override
    public GameState start() {
        throw new InvalidCommandException();
    }

    @Override
    public GameState gameSet() {
        throw new InvalidCommandException();
    }

    @Override
    public GameState move(Position from, Position to, Side side) {
        throw new InvalidCommandException();
    }

    @Override
    public GameState status() {
        return this;
    }

    @Override
    public boolean isGameSet() {
        return true;
    }

    @Override
    public Score score() {
        return Score.calculate(board());
    }
}
