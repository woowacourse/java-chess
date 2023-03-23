package chess.controller;

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
            GameSession.getGame();
        }
        return new Response(ResponseType.END);
    }

    public void validate(Request request) {
        if (request.getGameCommand() != GameCommand.END) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }
    }
}
