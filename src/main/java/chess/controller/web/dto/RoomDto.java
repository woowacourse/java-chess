package chess.controller.web.dto;

public class RoomDto {
    private final Long id;
    private final String name;

    public RoomDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
