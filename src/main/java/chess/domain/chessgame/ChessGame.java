package chess.domain.chessgame;

import chess.domain.board.Board;
import chess.domain.board.BoardInitializer;
import chess.domain.piece.Piece;
import chess.domain.piece.Team;
import chess.domain.position.Position;

import java.util.Map;

public final class ChessGame {
    private final Board board;
    private final Turn turn;
    private final GameState gameState;

    public ChessGame(Board board, Turn turn, GameState gameState) {
        this.board = board;
        this.turn = turn;
        this.gameState = gameState;
    }

    public ChessGame(Board board) {
        this(board, new Turn(), new GameState(!board.isKingDead()));
    }

    public ChessGame() {
        this(new Board());
    }

    public ChessGame(String pieces, String turn) {
        this(BoardInitializer.boardFromString(pieces), new Turn(turn), new GameState(!BoardInitializer.boardFromString(pieces).isKingDead()));
    }

    public boolean isRunning() {
        return gameState.isRunning();
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

    public void restartGame() {
        board.refresh();
        turn.refresh();
        gameState.refresh();
    }

    public Map<Position, Piece> board() {
        return board.unwrap();
    }

    public Team currentTurn() {
        return turn.now();
    }
}
