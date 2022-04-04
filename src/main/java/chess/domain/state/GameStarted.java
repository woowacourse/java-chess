package chess.domain.state;

import chess.domain.board.Board;

public abstract class GameStarted implements GameState {

    protected final Board board;

    protected GameStarted(Board board) {
        this.board = board;
    }

    @Override
    public double calculateBlackScore() {
        return board.calculateBlackScore();
    }

    @Override
    public double calculateWhiteScore() {
        return board.calculateWhiteScore();
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public boolean isBlackWin() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}
