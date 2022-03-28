package chess.controller;

import java.util.List;

public class GameCommandRequest {
    private final GameCommand gameCommand;
    private final List<String> body;

    private GameCommandRequest(GameCommand gameCommand, List<String> body) {
        this.gameCommand = gameCommand;
        this.body = body;
    }

    public static GameCommandRequest of(List<String> commandInputs) {
        checkRequestInputs(commandInputs);
        GameCommand command = GameCommand.findCommand(commandInputs.get(0));
        commandInputs.remove(0);
        checkRequestBody(commandInputs, command);
        return new GameCommandRequest(command, commandInputs);
    }

    private static void checkRequestBody(List<String> commandInputs, GameCommand command) {
        if (command.isMove() && commandInputs.size() != 2) {
            throw new IllegalArgumentException("move 요청에 내용이 없습니다.");
        }
    }

    private static void checkRequestInputs(List<String> commandInputs) {
        if (commandInputs.isEmpty()) {
            throw new IllegalArgumentException("잘못된 요청입니다.");
        }
    }

    public GameCommand getGameCommand() {
        return gameCommand;
    }

    public List<String> getBody() {
        return body;
    }
}
