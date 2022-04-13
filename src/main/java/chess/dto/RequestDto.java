package chess.dto;

public class RequestDto {

    private final String id;

    public RequestDto(final String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
