package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.GameResult;
import chess.domain.board.InitialState;
import chess.domain.position.Position;

public class ChessGame {
    private Board board;

    public ChessGame() {
        this.board = new InitialState();
    }

    public void initialize() {
        board = board.initialize();
    }

    public boolean isInitialized() {
        return board.isInitialized();
    }

    public void move(final String source, final String target) {
        final Position sourcePosition = Position.from(source);
        final Position targetPosition = Position.from(target);
        board = board.move(sourcePosition, targetPosition);
    }

    public GameResult getResult() {
        return board.getResult();
    }
}
