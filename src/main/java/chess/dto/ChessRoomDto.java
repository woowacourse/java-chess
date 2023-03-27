package chess.dto;

import chess.controller.ChessState;

public class ChessRoomDto {

    private final int id;
    private final int chessGameId;
    private final int playerId;
    private final ChessState state;

    private ChessRoomDto(final int id, final int chessGameId, final int playerId, final ChessState state) {
        this.id = id;
        this.chessGameId = chessGameId;
        this.playerId = playerId;
        this.state = state;
    }

    public static ChessRoomDto of(final int id, final int chessGameId, final int playerId, final String stateName) {
        final ChessState state = ChessState.bind(stateName);

        return new ChessRoomDto(id, chessGameId, playerId, state);
    }

    public int getId() {
        return id;
    }

    public int getChessGameId() {
        return chessGameId;
    }

    public ChessState getState() {
        return state;
    }
}
