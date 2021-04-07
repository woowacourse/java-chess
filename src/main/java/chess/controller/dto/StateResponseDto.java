package chess.controller.dto;

import chess.domain.manager.ChessManager;

public class StateResponseDto {

    private final String turnOwner;
    private final int turnNumber;
    private final boolean isPlaying;

    public StateResponseDto(String turnOwner, int turnNumber, boolean isPlaying) {
        this.turnOwner = turnOwner;
        this.turnNumber = turnNumber;
        this.isPlaying = isPlaying;
    }

    public static StateResponseDto toChessManager(ChessManager chessManager) {
        return new StateResponseDto(chessManager.turnOwner().name(), chessManager.turnNumber(), chessManager.isPlaying());
    }

    public String getTurnOwner() {
        return turnOwner;
    }

    public int getTurnNumber() {
        return turnNumber;
    }

    public boolean isPlaying() {
        return isPlaying;
    }
}
