package chess.controller;

public class RequestInfo {
    private final GameCommand gameCommand;
    private final String input;

    public RequestInfo(String input) {
        gameCommand = GameCommand.from(input);
        this.input = input;
    }

    public GameCommand getGameCommand() {
        return gameCommand;
    }

    public String getInput() {
        return input;
    }
}
