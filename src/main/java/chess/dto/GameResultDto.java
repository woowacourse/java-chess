package chess.dto;

import chess.domain.game.Game;
import chess.domain.game.statistics.GameResult;

public class GameResultDto {

    private final GameDto gameInfo;
    private final GameResult result;

    public GameResultDto(int gameId, Game game){
        this.gameInfo = game.toDtoOf(gameId);
        this.result =game.getResult();
    }

    public GameDto getGameInfo() {
        return gameInfo;
    }

    public GameResult getResult() {
        return result;
    }
}
