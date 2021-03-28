package chess.controller.command;

import chess.domain.board.position.Position;
import chess.manager.ChessGame;
import chess.view.OutputView;

public class End extends Command {
    public End(String line) {
        super(line);
    }

    @Override
    public Command read(String line) {
        final Menu menu = Menu.of(line);

        if (menu.isStart()) {
            return new Start(line);
        }

        throw new IllegalArgumentException("부적절한 명령어 입력입니다.");
    }

    @Override
    public void execute(final ChessGame chessGame) {
        chessGame.makeGameEnd();
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
