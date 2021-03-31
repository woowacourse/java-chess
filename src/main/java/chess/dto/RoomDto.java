package chess.dto;

public class RoomDto {
    private String id;
    private String name;

    public RoomDto(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
