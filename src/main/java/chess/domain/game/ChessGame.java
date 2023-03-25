package chess.domain.game;

import chess.domain.board.Position;
import chess.domain.piece.Piece;
import chess.domain.piece.property.Color;

import java.util.Map;

public final class ChessGame {

    private GameStatus gameStatus;
    private Long gameId;

    public ChessGame() {
        this.gameStatus = new Waiting();
    }

    public void startGame() {
        this.gameStatus = gameStatus.start();
    }

    public void load(GameStatus gameStatus) {
        this.gameStatus = this.gameStatus.load(gameStatus);
    }

    public void playTurn(Position source, Position target) {
        this.gameStatus = gameStatus.playTurn(source, target);
    }

    public void end() {
        this.gameStatus = gameStatus.end();
    }

    public boolean isOnGoing() {
        return gameStatus.isOnGoing();
    }

    public double computeScore(Color color) {
        return gameStatus.computeScore(color);
    }

    public Map<Position, Piece> getBoard() {
        return gameStatus.getBoard();
    }

    public String getTurn() {
        return gameStatus.getTurn().name();
    }

    public Long getGameId() {
        return gameId;
    }

    public void setGameId(final Long gameId) {
        this.gameId = gameId;
    }
}
