package chess.dto;

public class PlayerDto {

    private final int id;
    private final String name;

    private PlayerDto(final int id, final String name) {
        this.id = id;
        this.name = name;
    }

    public static PlayerDto of(final int id, final String name) {
        return new PlayerDto(id, name);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
