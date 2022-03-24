package chess.domain.board;

import chess.domain.board.state.BoardInitializer;
import chess.domain.board.state.BoardState;
import chess.domain.board.state.Winner;
import chess.domain.piece.Position;

public class Board {

    private BoardState state;

    public Board() {
        this.state = BoardInitializer.initBoard();
    }

    public Rank getRank(int rankLine) {
        return state.getRank(rankLine);
    }

    public void move(Position start, Position target) {
        state = state.move(start, target);
    }

    public boolean isBlackTurn() {
        return state.isBlackTurn();
    }

    public boolean isEnd() {
        return state.isEnd();
    }

    public void terminate() {
        this.state = state.terminate();
    }

    public Winner findWinner() {
        return state.findWinner();
    }

    public double getBlackScore() {
        return state.calculateBlackScore();
    }

    public double getWhiteScore() {
        return state.calculateWhiteScore();
    }
}
