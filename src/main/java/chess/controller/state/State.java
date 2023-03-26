package chess.controller.state;

import chess.dao.GameDao;
import chess.domain.board.Square;
import chess.domain.game.Game;

public abstract class State {
    private final Game game;
    private final GameDao gameDao;

    State(final Game game) {
        this.game = game;
        gameDao = new GameDao();
    }

    public Running start() {
        throw new IllegalStateException("게임을 시작할 수 없는 상태입니다.");
    }

    public State move(final Square source, final Square target) {
        throw new IllegalStateException("다음 턴으로 넘길 수 없는 상태입니다.");
    }

    public State status() {
        throw new IllegalStateException("상태를 볼 수 없는 상태입니다.");
    }

    public End end() {
        return new End(game);
    }

    protected Game game() {
        return game;
    }

    public boolean isRunning() {
        return true;
    }

    protected void saveGameHistory() {
        gameDao.addMove(game);
    }

    protected Game lastGame() {
        return gameDao.restoreLastGame();
    }
}
