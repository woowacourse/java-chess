package chess.gamestate;

import chess.command.Command;

public class Ready implements GameState {

    @Override
    public GameState operateCommand(String input) {
        if (Command.getByInput(input) == Command.START) {
            return new Running();
        }
        throw new IllegalArgumentException("start 이외의 명령은 입력할 수 없습니다.");
    }
}
