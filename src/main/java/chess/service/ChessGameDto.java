package chess.service;

public class ChessGameDto {
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

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status.toUpperCase();
    }

    public String getTurn() {
        return turn.toUpperCase();
    }
}
