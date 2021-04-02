package chess.controller.web.dto;

public class SaveRequestDto {
    private final long id;
    private final String pieces;

    public SaveRequestDto(long id, String pieces) {
        this.id = id;
        this.pieces = pieces;
    }

    public long getId() {
        return id;
    }

    public String getPieces() {
        return pieces;
    }
}
