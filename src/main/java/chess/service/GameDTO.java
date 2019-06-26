package chess.service;

public class GameDTO {
    private String name;
    private String id;

    public GameDTO(final String name, final int id) {
        this.name = name;
        this.id = String.valueOf(id);
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}
