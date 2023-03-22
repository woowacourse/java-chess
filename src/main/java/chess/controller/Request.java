package chess.controller;

public class Request {
    private final GameCommand gameCommand;
    private final String input;

    public Request(String input) {
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
