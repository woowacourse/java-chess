package domain.game;

import domain.board.Board;
import domain.board.Position;
import domain.game.state.Init;
import domain.game.state.State;

public class Game {
    private State state;
    private final Board board;

    public Game(final Board board) {
        this.state = new Init();
        this.board = board;
    }

    public void moveByPosition(final Position source, final Position target) {
        if (state.isInit() || state.isEnded()) {
            throw new IllegalStateException("게임 플레이 중이 아닙니다.");
        }
        board.moveByPosition(source, target);
    }

    public void start() {
        state = state.start();
    }

    public void end() {
        state = state.end();
    }

    public boolean isInit() {
        return state.isInit();
    }

    public boolean isStarted() {
        return state.isStarted();
    }

    public boolean isEnded() {
        return state.isEnded();
    }

    public boolean isNotEnded() {
        return state.isNotEnded();
    }

    public Board board() {
        return board;
    }
}
