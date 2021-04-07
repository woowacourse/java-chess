package chess.controller.dto;

import chess.domain.manager.ChessManager;

public class HistoryResponseDto {

    private final String moveCommand;
    private final String turnOwner;
    private final int turnNumber;
    private final boolean isPlaying;

    public HistoryResponseDto(String moveCommand, String turnOwner, int turnNumber, boolean isPlaying) {
        this.moveCommand = moveCommand;
        this.turnOwner = turnOwner;
        this.turnNumber = turnNumber;
        this.isPlaying = isPlaying;
    }

    public static HistoryResponseDto toChessManager(String moveCommand, ChessManager chessManager) {
        return new HistoryResponseDto(
                moveCommand,
                chessManager.turnOwner().name(),
                chessManager.turnNumber(),
                chessManager.isPlaying()
        );
    }

    public String getMoveCommand() {
        return moveCommand;
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
