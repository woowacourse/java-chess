package chess.domain.state;

import chess.domain.board.Board;
import chess.domain.board.Rank;

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
    public Rank getRank(int rankLine) {
        return board.getRank(rankLine);
    }

    @Override
    public Board getBoard() {
        return board;
    }
}
