package chess.service.dto;

public class ChessSaveRequestDto {
    private String name;

    public ChessSaveRequestDto(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
