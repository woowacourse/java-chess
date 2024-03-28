package domain.game.state;

import domain.game.TeamColor;
import java.util.EnumMap;

public final class GameEnd implements GameState {
    private static final EnumMap<TeamColor, GameEnd> CACHE = new EnumMap<>(TeamColor.class);

    private static final String GAME_END_MESSAGE = "게임이 종료되었습니다.";

    private final TeamColor winner;

    private GameEnd(TeamColor winner) {
        this.winner = winner;
    }

    public static GameEnd getInstance(TeamColor winner) {
        return CACHE.computeIfAbsent(winner, GameEnd::new);
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
    public TeamColor getWinner() {
        return winner;
    }
}
