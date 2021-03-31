package chess.domain.dto;

public class HistoryDto {
    private final String name;

    public HistoryDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
