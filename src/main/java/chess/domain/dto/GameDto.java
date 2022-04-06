package chess.domain.dto;

import chess.domain.game.ChessGame;
import chess.domain.state.Ready;
import chess.domain.state.Running;
import chess.domain.state.State;

public class GameDto {

    private final Long id;
    private final String state;
    private final String turn;

    public GameDto(Long id, String state, String turn) {
        this.id = id;
        this.state = state;
        this.turn = turn;
    }

    public static GameDto from(ChessGame chessGame) {
        return new GameDto(0L, convertToString(chessGame.getState()), chessGame.turn().name());
    }

    private static String convertToString(State state) {
        if (state instanceof Ready) {
            return "ready";
        }
        if (state instanceof Running) {
            return "running";
        }
        return "finished";
    }

    public Long getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getTurn() {
        return turn;
    }

    @Override
    public String toString() {
        return "GameDto{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", turn='" + turn + '\'' +
                '}';
    }
}

