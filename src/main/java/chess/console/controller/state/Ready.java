package chess.console.controller.state;

import chess.console.controller.GameCommand;

public class Ready implements GameState {
    @Override
    public GameState changeStateBy(GameCommand gameCommand) {
        if (gameCommand.isMove() || gameCommand.isStatus()) {
            throw new IllegalStateException("잘못된 명령어 실행입니다.");
        }
        if (gameCommand.isStart()) {
            return new Playing();
        }
        return new End();
    }
}
