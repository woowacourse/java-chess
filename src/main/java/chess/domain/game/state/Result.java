package chess.domain.game.state;

import chess.domain.game.ChessGame;
import chess.domain.game.state.attribute.StateType;
import chess.dto.CommandDto;
import chess.view.Command;

public final class Result extends DefaultState {
    private static final String INVALID_COMMEND_MESSAGE = "status 를 입력하세요.";

    public Result(ChessGame chessGame) {
        super(StateType.RESULT, chessGame);
    }

    @Override
    public State execute(CommandDto commandDto) {
        if (commandDto.getCommand() == Command.STATUS) {
            return new Status(chessGame);
        }
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }
}
