package chess.gamestate;

import chess.command.Command;

public class Running implements GameState {

    @Override
    public GameState operateCommand(String input) {
        Command command = Command.getByInput(input);
        if (command == Command.MOVE || command == Command.STATUS) {
            return new Running();
        }
        if (command == Command.END) {
            return new Finished();
        }
        throw new IllegalArgumentException("올바르지 않은 입력입니다.");
    }
}
