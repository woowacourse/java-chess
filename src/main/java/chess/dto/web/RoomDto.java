package chess.dto.web;

public class RoomDto {

    private static final int NAME_COUNT_MIN = 2;

    private final String id;
    private final String name;
    private final String white;
    private final String black;

    public RoomDto(String id, String name, String white, String black) {
        validateName(name);
        this.id = id;
        this.name = name;
        this.white = white;
        this.black = black;
    }

    private static void validateName(String name) {
        if (name.length() < NAME_COUNT_MIN) {
            throw new IllegalArgumentException("방 이름은 2글자 이상입니다.");
        }
    }

    public String getWhite() {
        return white;
    }

    public String getBlack() {
        return black;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
