package chess.domain.state;

import chess.domain.Board;
import chess.domain.piece.Color;

public class Ended implements State {

    @Override
    public State start() {
        return new Ended();
    }

    @Override
    public State end() {
        return new Ended();
    }

    @Override
    public State move(final String[] commands) {
        return new Ended();
    }

    @Override
    public State status() {
        return new Ended();
    }

    @Override
    public boolean isEnded() {
        return true;
    }

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public Color getColor() {
        throw new UnsupportedOperationException("[ERROR] 게임이 이미 끝났습니다.");
    }

    @Override
    public Board getBoard() {
        throw new UnsupportedOperationException("[ERROR] 게임이 이미 끝났습니다.");
    }

}
