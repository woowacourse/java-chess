package chess.controller;

import chess.dao.ChessDB;
import chess.domain.dto.BoardSaveDto;
import chess.domain.dto.ResultDto;
import chess.domain.game.Game;
import chess.domain.game.GameSession;
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
        try {
            validate(request);
            Game game = GameSession.getGame();
            ChessDB.saveBoard(BoardSaveDto.from(game.getBoard()));
            return new Response(ResponseType.STATUS, makeGameResultDto(game));
        } catch (IllegalArgumentException | IllegalStateException e) {
            return new Response(ResponseType.FAIL, e.getMessage());
        }
    }

    private ResultDto makeGameResultDto(Game game) {
        Result result = game.getResult();
        return new ResultDto(result.getGameResult().getMessage(), result.getWhiteScore().getScore(), result.getBlackScore().getScore());
    }

    private void validate(Request request) {
        if (request.getGameCommand() != GameCommand.STATUS) {
            throw new IllegalArgumentException("잘못된 커맨드 요청입니다.");
        }

        if (!GameSession.existGame()) {
            throw new IllegalStateException("현재 실행 중인 체스 게임이 없습니다.");
        }
    }
}
