package chess.domain.dto;

public class CommandDto {
    private final String data;

    public CommandDto(String data) {
        this.data = data;
    }

    public String data() {
        return data;
    }
}
