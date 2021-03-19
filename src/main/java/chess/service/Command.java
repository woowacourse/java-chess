package chess.service;

import chess.service.state.*;

public class Command {
    public static GameState gameState(String command) {
        if (command.equals("start")) {
            return new Playing();
        }
        if (command.startsWith("move")) {
            return new Move(command);
        }
        if (command.equals("end")) {
            return new End();
        }
        if (command.equals("status")) {
            return new Status();
        }
        throw new IllegalArgumentException("잘못된 입력값입니다.");
    }
}
