package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.coordinate.Coordinate;
import chess.domain.game.state.BlackTurn;
import chess.domain.game.state.End;
import chess.domain.game.state.Start;
import chess.domain.game.state.State;
import chess.domain.game.state.WhiteTurn;
import chess.domain.piece.Team;

public class ChessGame {

    public static final String END_STATE = "END";
    private State state;

    public ChessGame() {
        state = new Start();
    }

    public ChessGame(Board board, String state) {
        if (state.equals(END_STATE)) {
            this.state = new End(board);
            return;
        }
        if (state.equals(Team.WHITE.name())) {
            this.state = new WhiteTurn(board);
            return;
        }
        this.state = new BlackTurn(board);
    }

    public void start() {
        state = state.start();
    }

    public void move(Coordinate from, Coordinate to) {
        state = state.move(from, to);
    }

    public void end() {
        state = state.end();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public StatusCalculator status() {
        return new StatusCalculator(getBoard().getValue());
    }

    public Board getBoard() {
        return state.getBoard();
    }
}
