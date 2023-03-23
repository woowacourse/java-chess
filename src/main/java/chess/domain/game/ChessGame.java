package chess.domain.game;

import chess.domain.board.Board;
import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;

import java.util.Map;

public final class ChessGame {

    private GameStatus gameStatus;

    public ChessGame() {
        this.gameStatus = new Waiting();
    }

    public ChessGame(Board board, Color turn) {
        this.gameStatus = new Running(board, turn);
    }

    public void startGame() {
        this.gameStatus = gameStatus.start();
    }

    public void playTurn(Position source, Position target) {
        this.gameStatus = gameStatus.playTurn(source, target);
    }

    public Map<Position, Piece> getBoard() {
        return gameStatus.getBoard();
    }

    public void end() {
        this.gameStatus = gameStatus.end();
    }

    public boolean isOnGoing() {
        return gameStatus.isOnGoing();
    }

    public String getTurn() {
        return gameStatus.getTurn().name();
    }

    public static ChessGame load(Board board, Color turn) {
        return new ChessGame(board, turn);
    }
}
