package chess.controller.Command;

import chess.domain.board.position.Position;
import chess.manager.ChessManager;

public class EndCommand extends Command {
    EndCommand(String line) {
        super(line);
    }

    @Override
    public Command read(String line) {
        final Menu menu = Menu.of(line);

        if (menu.isStart()) {
            return new StartCommand(line);
        }

        throw new IllegalArgumentException("부적절한 명령어 입력입니다.");
    }

    @Override
    public void execute(ChessManager chessManager) {
        chessManager.makeGameEnd();
    }

    @Override
    public Position source() {
        throw new IllegalArgumentException("End에는 Parameter가 존재하지 않습니다.");
    }

    @Override
    public Position target() {
        throw new IllegalArgumentException("End에는 Parameter가 존재하지 않습니다.");
    }
}
