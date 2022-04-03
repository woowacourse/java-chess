package chess.dto;

public class RoomDto {

    private final String name;

    private RoomDto(final String name) {
        this.name = name;
    }

    public static RoomDto from(final String name) {
        return new RoomDto(name);
    }

    public String getName() {
        return name;
    }
}
