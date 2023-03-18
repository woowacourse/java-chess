package chess.domain.game;

import chess.domain.game.exception.ChessGameException;
import chess.domain.game.state.GameState;
import chess.domain.game.state.StartState;
import chess.domain.piece.Piece;
import chess.domain.piece.exception.IllegalPieceMoveException;
import java.util.List;

public class ChessGame {

    private static final int FILE_INDEX = 0;
    private static final int RANK_INDEX = 1;
    private static final int ORIGIN_INDEX = 1;
    private static final int TARGET_INDEX = 2;
    private static final int DESTINATION_INDEX = TARGET_INDEX;
    private Board board;
    private GameState gameState;

    public ChessGame() {
        gameState = StartState.getInstance();
    }

    public void start() {
        gameState = gameState.start();
        board = new Board();
    }

    public void move(List<String> command) {
        handleError(() -> {
            Position origin = Position.of(
                    command.get(ORIGIN_INDEX).charAt(FILE_INDEX),
                    command.get(ORIGIN_INDEX).charAt(RANK_INDEX)
            );
            Position destination = Position.of(
                    command.get(DESTINATION_INDEX).charAt(FILE_INDEX),
                    command.get(DESTINATION_INDEX).charAt(RANK_INDEX)
            );
            gameState.move(() -> gameState.move(() -> board.movePiece(origin, destination)));
        });
    }

    public List<List<Piece>> getPieces() {
        return board.getPieces();
    }

    public boolean isRunning() {
        return gameState.isRunning();
    }

    public void end() {
        gameState = gameState.end();
    }

    private void handleError(Runnable runnable) {
        try {
            runnable.run();
        } catch (IllegalPieceMoveException e) {
            throw new ChessGameException(e.getMessage(), e);
        }
    }
}
