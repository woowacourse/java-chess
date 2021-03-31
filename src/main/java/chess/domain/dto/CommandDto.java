package chess.domain.dto;

public class CommandDto {
    private final String data;
    private final String history_id;

    public CommandDto(String data, String history_id) {
        this.data = data;
        this.history_id = history_id;
    }

    public String data() {
        return data;
    }

    public String historyId() {
        return history_id;
    }
}
