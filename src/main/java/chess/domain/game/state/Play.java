package chess.domain.game.state;

import chess.controller.Command;
import chess.domain.board.position.Position;
import chess.domain.game.ChessGame;
import java.util.List;

public final class Play extends State {
    private static final String INVALID_COMMEND_MESSAGE = "end, move 만 입력할 수 있습니다.";

    public Play(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    protected State execute(String input) {
        Command command = Command.from(input);

        if (command == Command.END) {
            return new ExitEnd(chessGame);
        }
        if (command == Command.MOVE) {
            move(input, chessGame);
            return playOrResult(chessGame);
        }
        throw new IllegalArgumentException(INVALID_COMMEND_MESSAGE);
    }

    private void move(String input, ChessGame chessGame) {
        List<Position> positions = Position.inputToPositions(input);
        chessGame.play(positions.get(0), positions.get(1));
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
