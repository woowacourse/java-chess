package chess.domain.gamestate;

import chess.domain.ChessGame;
import chess.domain.Turn;
import chess.domain.command.Command;
import java.util.List;

public class Ready implements GameState {

    private final ChessGame chessGame;

    public Ready(ChessGame chessGame) {
        this.chessGame = chessGame;

    }

    @Override
    public GameState operateCommand(Command command, List<String> arguments) {
        if (command == Command.START) {
            chessGame.initialize();
            return new Running(chessGame, new Turn());
        }
        throw new IllegalArgumentException("start 이외의 명령은 입력할 수 없습니다.");
    }

    @Override
    public boolean isRunning() {
        return false;
    }
}
