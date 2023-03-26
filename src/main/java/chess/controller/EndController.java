package chess.controller;

import chess.controller.login.LoginSession;
import chess.dao.GameDao;
import chess.dao.PieceDao;
import chess.domain.dto.BoardSaveDto;
import chess.domain.game.Game;
import chess.domain.game.GameSession;

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
            String currentLoginId = LoginSession.getCurrentLoginId();
            GameDao.enrollGameOf(currentLoginId, currentLoginId, game.getBoard().getTurn().name());
            PieceDao.saveBoard(BoardSaveDto.from(game.getBoard(), currentLoginId));
        }
        return new Response(ResponseType.END);
    }

    public void validate(Request request) {
        if (request.getGameCommand() != GameCommand.END) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }
    }
}
