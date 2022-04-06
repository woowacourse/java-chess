package chess.service;

public class ChessGameDto {
    private int id;
    private final String status;
    private final String turn;

    public ChessGameDto(String status, String turn) {
        this.status = status;
        this.turn = turn;
    }

    public ChessGameDto(int id, String status, String turn) {
        this.id = id;
        this.status = status;
        this.turn = turn;
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status.toUpperCase();
    }

    public String getTurn() {
        return turn.toUpperCase();
    }
}
