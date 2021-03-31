package chess.dto;

public class RoomDto {
    private static final int NAME_COUNT_MIN = 2;

    private String id;
    private String name;

    public RoomDto(String id, String name) {
        validateName(name);
        this.id = id;
        this.name = name;
    }

    public RoomDto(String name) {
        new RoomDto("", name);
    }

    private static void validateName(String name) {
        if (name.length() < NAME_COUNT_MIN) {
            throw new IllegalArgumentException("방 이름은 2글자 이상입니다.");
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
