package chess.dao;

import chess.domain.piece.property.Color;

public class InMemoryChessGameDao implements ChessGameDao{

    private Color currentTurnColor;

    @Override
    public int makeGameRoom(final Color initialTurnColor) {
        this.currentTurnColor = initialTurnColor;
        return 0;
    }

    @Override
    public Color findCurrentTurnColor(final int gameRoomId) {
        return currentTurnColor;
    }

    @Override
    public void updateCurrentTurnColor(final int gameRoomId, final Color turnColor) {
        this.currentTurnColor = turnColor;
    }

    @Override
    public void removeGameDataFromDb(final int gameRoomId) {
        currentTurnColor = null;
    }
}
