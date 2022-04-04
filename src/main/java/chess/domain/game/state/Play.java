package chess.domain.game.state;

import chess.domain.game.ChessGame;
import chess.domain.game.state.attribute.StateType;
import chess.dto.CommandDto;
import chess.view.Command;

public final class Play implements State {
    private static final String INVALID_COMMEND_MESSAGE = "move 만 입력할 수 있습니다.";

    private final ChessGame chessGame;

    public Play(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    @Override
    public State execute(CommandDto commandDto) {
        if (commandDto.getCommand() == Command.MOVE) {
            chessGame.play(commandDto.toSourcePosition(), commandDto.toTargetPosition());
            return playOrResult(chessGame);
        }
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }

    public StateType getType() {
        return StateType.PLAY;
    }

    private State playOrResult(ChessGame chessGame) {
        if (chessGame.isFinished()) {
            return new End();
        }
        return new Play(chessGame);
    }
}
