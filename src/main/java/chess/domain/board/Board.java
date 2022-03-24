package chess.domain.board;

import chess.domain.board.state.BoardInitializer;
import chess.domain.board.state.BoardState;
import chess.domain.piece.Position;

public class Board {

    private static final int RANK_CAPACITY = 8;

    private BoardState state;

    public Board() {
        this.state = BoardInitializer.initBoard();
    }

    public void initialize() {
        this.state = BoardInitializer.initBoard();
    }

    public Rank getRank(int rankLine) {
        return state.getRank(rankLine);
    }

    public void move(Position start, Position target) {
        state = state.move(start, target);
    }
}
