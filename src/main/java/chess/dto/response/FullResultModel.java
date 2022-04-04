package chess.dto.response;

import chess.domain.game.Game;
import chess.domain.game.GameResult;

public class FullResultModel {

    private final FullGameModel gameInfo;
    private final GameResult result;

    public FullResultModel(int gameId, Game game){
        this.gameInfo = new FullGameModel(gameId, game);
        this.result =game.getResult();
    }

    public FullGameModel getGameInfo() {
        return gameInfo;
    }

    public GameResult getResult() {
        return result;
    }
}
