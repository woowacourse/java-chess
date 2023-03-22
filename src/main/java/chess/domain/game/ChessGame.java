package chess.domain.game;

import chess.domain.board.Position;
import chess.domain.piece.Piece;

import java.util.Map;

public final class ChessGame {

    private GameStatus gameStatus;

    public ChessGame() {
        this.gameStatus = new Waiting();
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

    public void save() {
        this.gameStatus = gameStatus.save();
    }
}
