package chess.model;

import chess.domain.board.piece.Color;
import chess.domain.game.Game;

public class GameModel {

    private final int id;
    private final GameState state;

    private GameModel(int id, GameState state) {
        this.id = id;
        this.state = state;
    }

    public static GameModel ofRunning(int gameId, Game game) {
        if (game.getCurrentTurnColor() == Color.WHITE) {
            return new GameModel(gameId, GameState.WHITE_TURN);
        }
        return new GameModel(gameId, GameState.BLACK_TURN);
    }

    public static GameModel ofOver(int gameId) {
        return new GameModel(gameId, GameState.OVER);
    }

    public int getId() {
        return id;
    }

    public GameState getState() {
        return state;
    }

    private enum GameState {
        WHITE_TURN("White"),
        BLACK_TURN("Black"),
        OVER("게임 종료");

        private final String value;

        GameState(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
