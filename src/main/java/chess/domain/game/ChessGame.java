package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.position.Position;
import chess.domain.game.state.Init;
import chess.domain.game.state.State;
import java.util.List;

public class ChessGame {

    private State state;

    public ChessGame(final Board board) {
        this(new Init(board));
    }

    public ChessGame(final State state) {
        this.state = state;
    }

    public void start() {
        state = state.start();
    }

    public void move(final Position source, final Position target) {
        state.moveIfValidColor(source, target);
        state = state.passTurn();
    }

    public void end() {
        state = state.end();
    }

    public Board board() {
        return state.board();
    }

    public boolean isInit() {
        return state.isInit();
    }

    public boolean isRunning() {
        return state.isRunning();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public String winner() {
        return state.winner();
    }

    public boolean isNotEnd() {
        return state.isNotEnd();
    }

    public List<Position> movablePath(final Position position) {
        return state.movablePath(position);
    }

    public String state() {
        return state.state();
    }

    public Score score() {
        ScoreCalculator scoreCalculator = new ScoreCalculator(state.board());
        return new Score(scoreCalculator.totalWhiteScore(), scoreCalculator.totalBlackScore());
    }
}
