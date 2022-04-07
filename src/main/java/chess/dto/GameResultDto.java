package chess.dto;

import chess.domain.game.Game;
import chess.domain.game.statistics.GameResult;
import java.util.Objects;

public class GameResultDto {

    private final GameDto gameInfo;
    private final GameResult result;

    public GameResultDto(int gameId, Game game) {
        this.gameInfo = game.toDtoOf(gameId);
        this.result = game.getResult();
    }

    public GameDto getGameInfo() {
        return gameInfo;
    }

    public GameResult getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameResultDto that = (GameResultDto) o;
        return Objects.equals(gameInfo, that.gameInfo)
                && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameInfo, result);
    }

    @Override
    public String toString() {
        return "GameResultDto{" + "gameInfo=" + gameInfo + ", result=" + result + '}';
    }
}
