package chess.domain.game;

import chess.domain.Side;
import chess.domain.board.Board;
import chess.domain.command.Command;
import chess.domain.gamestate.State;
import chess.exception.InvalidCommandException;
import chess.view.OutputView;

public class ChessGame {
    private State state;

    public ChessGame(State state) {
        this.state = state;
    }

    public void start() {
        state = state.start();
    }

    public void execute(Command command) {
        if (command.isMove()) {
            state = state.move(command.source(), command.target());
            return;
        }
        if (command.isStatus()) {
            // TODO status(), score() 둘 중 하나만 있으면 됨
            state = state.status();
            OutputView.print(state.score()); // TODO 컨트롤러로 이동
            return;
        }
        if (command.isEnd()) {
            state = state.finished();
            return;
        }

        throw new InvalidCommandException();
    }

    public State state() {
        return state;
    }

    public Board board() {
        return state.board();
    }

    public Side currentTurn() {
        return state.currentTurn();
    }

    public Side winner() {
        return state.winner();
    }
}
