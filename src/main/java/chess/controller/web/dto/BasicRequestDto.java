package chess.controller.web.dto;

public class BasicRequestDto {
    private final long id;

    public BasicRequestDto(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
