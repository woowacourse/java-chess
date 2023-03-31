package chess.dto;

public class BoardDto {
    private final String source;
    private final String piece;

    public BoardDto(String source, String piece) {
        this.source = source;
        this.piece = piece;
    }

    public String getSource() {
        return source;
    }

    public String getPiece() {
        return piece;
    }

}
