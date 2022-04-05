package chess.dao.dto;

public class PlayerDto {

    private Long id;
    private String colorName;
    private String pieces;

    public PlayerDto(final Long id, final String colorName, final String pieces) {
        this.id = id;
        this.colorName = colorName;
        this.pieces = pieces;
    }

    public Long getId() {
        return id;
    }

    public String getColorName() {
        return colorName;
    }

    public String getPieces() {
        return pieces;
    }
}
