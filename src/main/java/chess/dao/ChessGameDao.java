package chess.dao;

import chess.domain.piece.property.Color;

public interface ChessGameDao {

    void makeGameRoom(Color initialTurnColor);

    Color findCurrentTurnColor();

    void updateCurrentTurnColor(final Color turnColor);

    void removeGameDataFromDb();

    int getGameRoomId();
}
