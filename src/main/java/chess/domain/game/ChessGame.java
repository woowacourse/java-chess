package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.GameResult;
import chess.domain.board.Start;

public class ChessGame {
    private Board board;

    public ChessGame() {
        this.board = new Start();
    }

    public void initialize() {
        board = board.initialize();
    }

    public boolean isInitialized() {
        return board.isInitialized();
    }

    public void move(final String source, final String target) {
        board = board.move(source, target);
    }

    public GameResult getResult() {
        return board.getResult();
    }
}
