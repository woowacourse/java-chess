package chess.domain.game.state;

import chess.domain.board.Board;
import chess.domain.board.position.Position;

public class End extends Ended {

    public End(Board board) {
        super(board);
    }

    @Override
    public State start() {
        throw new IllegalStateException();
    }

    @Override
    public void moveIfValidColor(Position source, Position target) {
        throw new IllegalStateException();
    }

    @Override
    public State passTurn() {
        throw new IllegalStateException();
    }

    @Override
    public Board board() {
        throw new IllegalStateException();
    }

    @Override
    public String winner() {
        throw new IllegalStateException();
    }

    @Override
    public State end() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isInit() {
        return false;
    }

    @Override
    public boolean isRunning() {
        return false;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isNotEnd() {
        return false;
    }
}
