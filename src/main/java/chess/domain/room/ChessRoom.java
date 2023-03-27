package chess.domain.room;

import chess.controller.ChessState;

import java.util.Objects;

public class ChessRoom {

    private final int id;
    private final int gameId;
    private final int playerId;
    private final ChessState state;

    private ChessRoom(final int id, final int gameId, final int playerId, final ChessState state) {
        this.id = id;
        this.gameId = gameId;
        this.playerId = playerId;
        this.state = state;
    }

    public static ChessRoom of(final int id, final int gameId, final int playerId, final String state) {
        ChessState chessState = ChessState.bind(state);
        return new ChessRoom(id, gameId, playerId, chessState);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final ChessRoom chessRoom = (ChessRoom) o;
        return id == chessRoom.id && gameId == chessRoom.gameId && playerId == chessRoom.playerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gameId, playerId);
    }

    public int getId() {
        return id;
    }

    public int getGameId() {
        return gameId;
    }

    public ChessState getState() {
        return state;
    }
}
