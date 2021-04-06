package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.piece.Color;
import chess.domain.piece.Position;

public class ChessGame {

    private final Board board;
    private State state;

    public ChessGame(final Board board) {
        this.board = board;
        this.state = new Ready(this);
    }

    public void changeState(State state) {
        this.state = state;
    }

    public void move(Position source, Position target) {
        state.move(source, target);
    }

    public void moveAndCatchPiece(final Color color, final Position source, final Position target) {
        board.movePiece(color, source, target);

        if (isCaughtKing()) {
            changeState(new End(this));
            return;
        }
        changeState(state.nextTurn());
    }

    public void end() {
        state.end();
    }

    public void start() {
        state.start();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public boolean isCaughtKing() {
        return board.isCaughtKing();
    }

    public Board getBoard() {
        return board;
    }

    public double getWhiteScore() {
        return board.getWhiteScore();
    }

    public double getBlackScore() {
        return board.getBlackScore();
    }

    public State getState() {
        return state;
    }

}
