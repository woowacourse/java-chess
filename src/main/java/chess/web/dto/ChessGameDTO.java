package chess.web.dto;

public class ChessGameDTO {

    private String id;
    private String name;

    public ChessGameDTO(String id, String name) {
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
