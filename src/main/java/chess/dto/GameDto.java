package chess.dto;

public class GameDto {
    public static final GameDto EMPTY = new GameDto(null, null, null);

    private final Integer id;
    private final Boolean status;
    private final String color;

    private GameDto(final Integer id, final Boolean status, final String color) {
        this.id = id;
        this.status = status;
        this.color = color;
    }

    public static GameDto from(final int id, final Boolean status, final String color) {
        return new GameDto(id, status, color);
    }

    public static GameDto of(final int id) {
        return new GameDto(id, null, null);
    }

    public int getId() {
        return id;
    }

    public boolean isEnd() {
        return status;
    }

    public String getColor() {
        return color;
    }
}
