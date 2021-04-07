package chess.controller.dto;

public class RoomDto {
    private final Long id;
    private final String name;

    public RoomDto(final Long id, final String name) {
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

