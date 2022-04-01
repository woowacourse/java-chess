package chess;

import chess.domain.GameCommand;
import chess.domain.StatusScore;
import chess.domain.board.Position;
import chess.domain.board.Positions;
import chess.domain.gamestate.Ready;
import chess.domain.gamestate.State;
import chess.domain.piece.Piece;
import chess.view.InputView;
import chess.view.OutputView;
import java.util.Map;

public class ChessGame {

    private State state;
    public boolean isEnd = false;

    public ChessGame() {
        state = new Ready();
    }

    public void run() {
        OutputView.printStartMessage();

        while (!isEnd()) {
            final String command = InputView.inputCommand();
            final GameCommand gameCommand = GameCommand.from(command);

            gameCommand.execute(command, this);
        }
    }

    public boolean isEnd() {
        return isEnd;
    }

    public void start() {
        state = state.start();
    }

    public void move(final Positions movePositions) {
        state = state.move(movePositions);
    }

    public boolean isNotRunning() {
        return !state.isRunning();
    }

    public void turnOff() {
        isEnd = true;
    }

    public void end() {
        state = state.end();
    }

    public StatusScore calculateStatus() {
        return state.calculateStatus();
    }

    public Map<Position, Piece> getBoard() {
        return state.getBoard();
    }
}
