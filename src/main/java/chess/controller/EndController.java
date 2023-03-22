package chess.controller;

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
        return new Response(ResponseType.END);
    }

    public void validate(Request request) {
        if (request.getGameCommand() != GameCommand.END) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }
    }
}
