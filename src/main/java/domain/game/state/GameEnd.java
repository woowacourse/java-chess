package domain.game.state;

import domain.game.Board;
import domain.game.MoveResponse;
import domain.game.TeamColor;
import domain.position.Position;

public class GameEnd implements GameState {
    private static final String GAME_END_MESSAGE = "게임이 종료되었습니다.";

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
        throw new IllegalStateException(GAME_END_MESSAGE);
    }

    @Override
    public TeamColor currentTurn() {
        throw new IllegalStateException(GAME_END_MESSAGE);
    }

    @Override
    public MoveResponse move(Board board, Position source, Position destination) {
        throw new IllegalStateException(GAME_END_MESSAGE);
    }

    @Override
    public TeamColor getWinner() {
        return winner;
    }
}
