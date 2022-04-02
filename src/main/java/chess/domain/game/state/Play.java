package chess.domain.game.state;

import chess.domain.game.ChessGame;
import chess.domain.game.state.attribute.StateType;
import chess.dto.CommandDto;
import chess.view.Command;

public final class Play extends DefaultState {
    private static final String INVALID_COMMEND_MESSAGE = "end, status, move 만 입력할 수 있습니다.";

    public Play(ChessGame chessGame) {
        super(StateType.PLAY, chessGame);
    }

    @Override
    public State execute(CommandDto commandDto) {
        if (commandDto.getCommand() == Command.END) {
            return new End(chessGame);
        }
        if (commandDto.getCommand() == Command.STATUS) {
            return new Status(chessGame);
        }
        if (commandDto.getCommand() == Command.MOVE) {
            chessGame.play(commandDto.toSourcePosition(), commandDto.toTargetPosition());
            return playOrResult(chessGame);
        }
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }

    private State playOrResult(ChessGame chessGame) {
        if (chessGame.isFinished()) {
            return new Result(chessGame);
        }
        return new Play(chessGame);
    }
}
