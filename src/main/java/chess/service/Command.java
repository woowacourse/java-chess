package chess.service;

import chess.service.state.*;

public class Command {

    private static final String START = "start";
    private static final String MOVE = "move";
    private static final String END = "end";
    private static final String STATUS = "status";

    public static GameState gameState(String command) {
        if (command.equals(START)) {
            return new Playing();
        }
        if (command.startsWith(MOVE)) {
            return new Move(command);
        }
        if (command.equals(END)) {
            return new End();
        }
        if (command.equals(STATUS)) {
            return new Status();
        }
        throw new IllegalArgumentException("잘못된 입력값입니다.");
    }
}
