package chess.controller.game;

import chess.controller.Controller;
import chess.controller.GameCommand;
import chess.controller.Request;
import chess.controller.Response;
import chess.controller.ResponseType;
import chess.controller.login.LoginSession;
import chess.controller.room.GameSession;
import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.dto.BoardSaveDto;
import chess.domain.game.Game;

public class EndController implements Controller {
    private final static EndController INSTANCE = new EndController();

    private EndController() {
    }

    public static EndController getInstance() {
        return INSTANCE;
    }

    @Override
    public Response execute(Request request) {
        validate(request);
        if (GameSession.existGame()) {
            Game game = GameSession.getGame();
            String currentPlayingRoomName = LoginSession.getCurrentPlayingRoomName();
            String gameId = GameDao.getGameIdOf(currentPlayingRoomName);
            GameDao.updateTurn(gameId, game.getBoard().getTurn().name());
            PieceDao.saveBoard(BoardSaveDto.from(game.getBoard(), gameId));
        }
        return new Response(ResponseType.END);
    }

    public void validate(Request request) {
        if (request.getGameCommand() != GameCommand.END) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }
    }
}
