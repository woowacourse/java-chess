package chess.domain.game.state;

import chess.domain.board.position.Position;
import chess.domain.game.ChessGame;
import chess.dto.CommandDto;
import java.util.List;

public final class Play extends State {
    private static final String INVALID_COMMEND_MESSAGE = "end, move 만 입력할 수 있습니다.";

    public Play(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    protected State execute(CommandDto commandDto) {
        if (commandDto.getCommand() == Command.END) {
            return new ExitFinished(chessGame);
        }
        if (commandDto.getCommand() == Command.MOVE) {
            chessGame.play(commandDto.ToSourcePosition(), commandDto.ToTargetPosition());
            return playOrResult(chessGame);
        }
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }

    private State playOrResult(ChessGame chessGame) {
        if (chessGame.isPlaying()) {
            return new Play(chessGame);
        }
        return new Result(chessGame);
    }

    @Override
    public boolean isPlay() {
        return true;
    }
}
