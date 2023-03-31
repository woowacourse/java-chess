package chess.controller.game;

import chess.controller.Controller;
import chess.controller.Request;
import chess.controller.Response;
import chess.controller.ResponseType;
import chess.controller.room.GameSession;
import chess.domain.dto.ResultDto;
import chess.domain.game.Game;
import chess.domain.game.Result;

public class StatusController implements Controller {
    private final static StatusController INSTANCE = new StatusController();

    private StatusController() {
    }

    public static StatusController getInstance() {
        return INSTANCE;
    }

    @Override
    public Response execute(Request request) {
        if (!GameSession.existGame()) {
            return new Response(ResponseType.FAIL, "게임이 없는 경우 상태를 출력할 수 없습니다.");
        }
        Game game = GameSession.getGame();
        return new Response(ResponseType.STATUS, makeGameResultDto(game));
    }

    private ResultDto makeGameResultDto(Game game) {
        Result result = game.getResult();
        return new ResultDto(result.getGameResult().getMessage(), result.getWhiteScore().getScore(), result.getBlackScore().getScore());
    }

}
