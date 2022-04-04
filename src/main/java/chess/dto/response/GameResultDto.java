package chess.dto.response;

import chess.domain.game.Game;
import chess.domain.game.GameResult;

public class GameResultDto {

    private final GameDto gameInfo;
    private final GameResult result;

    public GameResultDto(int gameId, Game game){
        this.gameInfo = new GameDto(gameId, game);
        this.result =game.getResult();
    }

    public GameDto getGameInfo() {
        return gameInfo;
    }

    public GameResult getResult() {
        return result;
    }
}
