package chess.service.dto;

public class ChessGameDto {
    private int id;
    private String name;
    private final String status;
    private final String turn;

    public ChessGameDto(String status, String turn) {
        this.status = status;
        this.turn = turn;
    }

    public ChessGameDto(String name, String status, String turn) {
        this.status = status;
        this.turn = turn;
        this.name = name;
    }

    public ChessGameDto(int id, String status, String turn) {
        this.id = id;
        this.status = status;
        this.turn = turn;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status.toUpperCase();
    }

    public String getTurn() {
        return turn.toUpperCase();
    }

    public int getId() {
        return id;
    }
}
