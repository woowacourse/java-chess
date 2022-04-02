package chess.domain.game.state;

import chess.domain.game.ChessGame;
import chess.domain.game.state.attribute.StateType;
import chess.dto.CommandDto;
import chess.view.Command;

public final class Init extends DefaultState {
    private static final String INVALID_COMMEND_MESSAGE = "end 혹은 start 만 입력할 수 있습니다.";

    public Init(ChessGame chessGame) {
        super(StateType.INIT, chessGame);
    }

    @Override
    public State execute(CommandDto commandDto) {
        if (commandDto.getCommand() == Command.END) {
            return new End(chessGame);
        }
        if (commandDto.getCommand() == Command.START) {
            return new Play(chessGame);
        }
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }
}
