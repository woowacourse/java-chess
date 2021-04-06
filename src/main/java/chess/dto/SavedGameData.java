package chess.dto;

import chess.domain.piece.Color;

public class SavedGameData {
    private final ChessBoardDto chessBoardDto;
    private final Color currentTurnColor;

    public SavedGameData(ChessBoardDto chessBoardDto, Color currentTurnColor) {
        this.chessBoardDto = chessBoardDto;
        this.currentTurnColor = currentTurnColor;
    }

    public ChessBoardDto getChessBoardDto() {
        return chessBoardDto;
    }

    public Color getCurrentTurnColor() {
        return currentTurnColor;
    }
}
