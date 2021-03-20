package chess.domain.gamestate;

import chess.domain.Board;
import chess.domain.Score;
import chess.domain.Side;
import chess.domain.position.Position;
import chess.exception.InvalidCommandException;

public class Ready extends GameState {

    public Ready(Board board) {
        super(board);
    }

    @Override
    public GameState start() {
        return new Running(board());
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
}
