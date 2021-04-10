package chess.controller.web.dto.history;

import chess.domain.manager.ChessManager;

public class HistoryResponseDto {

    private final String moveCommand;
    private final String turnOwner;
    private final int turnNumber;
    private final boolean isPlaying;

    public HistoryResponseDto(final String moveCommand, final String turnOwner, final int turnNumber, final boolean isPlaying) {
        this.moveCommand = moveCommand;
        this.turnOwner = turnOwner;
        this.turnNumber = turnNumber;
        this.isPlaying = isPlaying;
    }

    public static HistoryResponseDto from(final String moveCommand, final ChessManager chessManager) {
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
