package chess.dao;

import chess.domain.piece.property.Color;

public interface ChessGameDao {

    int makeGameRoom(final Color initialTurnColor);

    Color findCurrentTurnColor(final int gameRoomId);

    void updateCurrentTurnColor(final int gameRoomId, final Color turnColor);

    void removeGameDataFromDb(final int gameRoomId);
}
