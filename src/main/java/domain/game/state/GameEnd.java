package domain.game.state;

import domain.game.Board;
import domain.game.MoveResponse;
import domain.game.TeamColor;
import domain.position.Position;

public class GameEnd implements GameState {
    private final TeamColor winner;

    public GameEnd(TeamColor winner) {
        this.winner = winner;
    }

    @Override
    public boolean isContinuable() {
        return false;
    }

    @Override
    public boolean isTurnOf(TeamColor teamColor) {
        throw new IllegalStateException("게임이 종료되었습니다.");
    }

    @Override
    public TeamColor currentTurn() {
        throw new IllegalStateException("게임이 종료되었습니다.");
    }

    @Override
    public MoveResponse move(Board board, Position source, Position destination) {
        throw new IllegalStateException("게임이 종료되었습니다.");
    }

    @Override
    public TeamColor getWinner() {
        return winner;
    }
}
