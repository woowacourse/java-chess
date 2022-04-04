package web.dto;

public class ChessGameDto {

    private final int id;
    private final String name;

    public ChessGameDto(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
