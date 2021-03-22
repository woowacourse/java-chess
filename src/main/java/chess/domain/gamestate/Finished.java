package chess.domain.gamestate;

import chess.domain.Turn;
import chess.domain.board.Point;

public class Finished implements GameState {

    private static final IllegalArgumentException EXCEPTION =
        new IllegalArgumentException("올바르지 않은 입력입니다.");

    @Override
    public GameState start() {
        throw EXCEPTION;
    }

    @Override
    public GameState move(Point source, Point destination, Turn turn) {
        throw EXCEPTION;
    }

    @Override
    public GameState end() {
        throw EXCEPTION;
    }

    @Override
    public GameState status() {
        throw EXCEPTION;
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
