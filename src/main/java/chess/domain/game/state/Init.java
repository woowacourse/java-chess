package chess.domain.game.state;

import chess.domain.game.ChessGame;
import chess.dto.CommandDto;

public final class Init extends State {
    private static final String INVALID_COMMEND_MESSAGE = "end 혹은 start 만 입력할 수 있습니다.";

    public Init(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    protected State execute(CommandDto commandDto) {
        if (commandDto.getCommand() == Command.END) {
            return new ExitFinished(chessGame);
        }
        if (commandDto.getCommand() == Command.START) {
            chessGame.start();
            return new Play(chessGame);
        }
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }
}
