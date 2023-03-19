package chess.domain.game;

import chess.domain.board.Position;
import chess.domain.board.Squares;

import java.util.List;

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

    public List<Squares> getBoard() {
        return gameStatus.getBoard();
    }

    public void end() {
        this.gameStatus = gameStatus.end();
    }

    public boolean isOnGoing() {
        return gameStatus.isOnGoing();
    }
}
