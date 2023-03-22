package chess.domain.game;

import chess.domain.game.exception.ChessGameException;
import chess.domain.game.state.GameState;
import chess.domain.game.state.StartState;
import chess.domain.piece.Piece;
import chess.domain.piece.exception.IllegalPieceMoveException;
import java.util.List;

public class ChessGame {

    private Board board;
    private GameState gameState;

    public ChessGame() {
        gameState = StartState.getInstance();
    }

    public void start() {
        gameState = gameState.start();
        board = new Board();
    }

    public void move(String origin, String destination) {
        gameState = gameState.move();
        try {
            board.movePiece(Position.from(origin), Position.from(destination));
        } catch (IllegalPieceMoveException e) {
            throw new ChessGameException(e.getMessage(), e);
        }
    }

    public List<List<Piece>> getPieces() {
        return board.getPieces();
    }

    public void end() {
        gameState = gameState.end();
    }
}
