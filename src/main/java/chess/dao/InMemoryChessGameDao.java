package chess.dao;

import chess.domain.piece.property.Color;

public class InMemoryChessGameDao implements ChessGameDao{

    private Color currentTurnColor;

    @Override
    public void makeGameRoom(final Color initialTurnColor) {
        this.currentTurnColor = initialTurnColor;
    }

    @Override
    public Color findCurrentTurnColor() {
        return currentTurnColor;
    }

    @Override
    public void updateCurrentTurnColor(final Color turnColor) {
        this.currentTurnColor = turnColor;
    }

    @Override
    public void removeGameDataFromDb() {
        currentTurnColor = null;
    }

    @Override
    public int getGameRoomId() {
        return 1;
    }
}
