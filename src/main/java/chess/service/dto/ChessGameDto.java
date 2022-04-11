package chess.service.dto;

public class ChessGameDto {
    private final String status;
    private final String turn;
    private int id;
    private String name;

    public ChessGameDto(int id, String name, String status, String turn) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.turn = turn;
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
