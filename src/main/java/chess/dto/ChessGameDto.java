package chess.dto;

public class ChessGameDto {
    private String currentColor;
    private String[] pieceNames;

    public ChessGameDto(String currentColor, String[] pieceNames) {
        this.currentColor = currentColor;
        this.pieceNames = pieceNames;
    }

    public String getCurrentColor() {
        return this.currentColor;
    }

    public String[] getPieceNames() {
        return this.pieceNames;
    }
}
