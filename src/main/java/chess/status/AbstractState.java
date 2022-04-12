package chess.status;

import chess.game.Board;
import chess.piece.detail.Color;

public abstract class AbstractState implements State {

    protected Board board;
    protected Color turn;

    AbstractState() {
    }

    AbstractState(final Board board, final Color turn) {
        this.board = board;
        this.turn = turn;
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public boolean canMove() {
        return false;
    }

    @Override
    public boolean isGameEnd() {
        return board.isKingDead();
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public Color getTurn() {
        return turn;
    }
}
