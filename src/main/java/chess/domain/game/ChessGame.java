package chess.domain.game;

import chess.domain.game.exception.ChessGameException;
import chess.domain.game.state.GameState;
import chess.domain.game.state.StartState;
import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.piece.exception.IllegalPieceMoveException;
import java.util.List;
import java.util.Map;

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

    public void move(Position origin, Position destination) {
        gameState = gameState.move();
        try {
            board.movePiece(origin, destination);
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

    public Map<Color, Integer> getStatus() {
        return null;
    }
}
