package web.dao;

import chess.piece.Color;
import web.dto.GameStatus;

public class GameStatusDao {

    private GameStatus status;
    private Color winner;

    public GameStatus findGameStatus() {
        return status;
    }

    public void saveGameStatus(GameStatus status) {
        this.status = status;
    }

    public void updateGameStatus(GameStatus status) {
        this.status = status;
    }

    public Color findWinner() {
        return winner;
    }

    public void saveWinner(Color color) {
        winner = color;
    }

    public void deleteAll() {
        winner = null;
        status = null;
    }
}
