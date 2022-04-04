package chess.domain.game.state;

import chess.domain.game.ChessGame;
import chess.domain.game.state.attribute.StateType;
import chess.dto.CommandDto;
import chess.view.Command;

public final class Init implements State {
    private static final String INVALID_COMMEND_MESSAGE = "end 혹은 start 만 입력할 수 있습니다.";

    private final ChessGame chessGame;

    public Init(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public State execute(CommandDto commandDto) {
        if (commandDto.getCommand() == Command.END) {
            return new End();
        }
        if (commandDto.getCommand() == Command.START) {
            return new Play(chessGame);
        }
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }

    public StateType getType() {
        return StateType.INIT;
    }
}
