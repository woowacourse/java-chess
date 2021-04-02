package chess.controller.web.dto;

public class SaveRequestDto {
    private final String pieces;

    public SaveRequestDto(String pieces) {
        this.pieces = pieces;
    }

    public String getPieces() {
        return pieces;
    }
}
