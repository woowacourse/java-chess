package chess.domain.game.state;

import chess.domain.game.ChessGame;
import chess.dto.CommandDto;

public final class Result extends AbstractState {
    private static final String INVALID_COMMEND_MESSAGE = "status 를 입력하세요.";

    public Result(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public AbstractState execute(CommandDto commandDto) {
        if (commandDto.getCommand() == Command.STATUS) {
            return new StatusFinished(chessGame);
        }
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }
}
