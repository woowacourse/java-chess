package chess.domain.chessgame;

import chess.domain.Turn;
import chess.domain.board.Board;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.Map;

public final class ChessGame {
    private final Board board;
    private final Turn turn;
    private final GameState gameState;

    public ChessGame(Board board) {
        this.board = board;
        this.turn = new Turn();
        this.gameState = new GameState();
    }

    public ChessGame() {
        this(new Board());
    }

    public boolean isGameOver() {
        return gameState.isGameOver();
    }

    public void move(final Position source, final Position target) {
        board.move(source, target, turn.now());
        prepareNextTurn();
    }

    private void prepareNextTurn() {
        if (board.isKingDead()) {
            endGame();
            return;
        }
        turn.next();
    }

    public void endGame() {
        gameState.endGame();
    }

    public Map<Position, Piece> board() {
        return board.unwrap();
    }
}
