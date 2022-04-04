package chess.dto;

import chess.domain.game.ChessGame;
import chess.domain.game.state.Ready;
import chess.domain.game.state.Started;
import chess.domain.game.state.State;
import chess.domain.piece.Color;

public class GameDto {

    private final Integer id;
    private final String state;
    private final String turn;

    public GameDto(final String state, final String turn) {
        this(null, state, turn);
    }

    public GameDto(final Integer id, final String state, final String turn) {
        this.id = id;
        this.state = state;
        this.turn = turn;
    }

    public static GameDto of(final Integer id, final State state, final Color turn) {
        return new GameDto(id, createState(state), turn.getName());
    }

    public static GameDto of(final ChessGame chessGame) {
        return new GameDto(null, createState(chessGame.getState()), chessGame.getTurnName());
    }

    private static String createState(final State state) {
        if (state instanceof Ready) {
            return "Ready";
        }
        if (state instanceof Started) {
            return "Started";
        }
        return "Ended";
    }

    public Integer getId() {
        return id;
    }

    public String getState() {
        return state;
    }

    public String getTurn() {
        return turn;
    }
}
