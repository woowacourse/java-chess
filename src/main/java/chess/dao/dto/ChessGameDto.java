package chess.dao.dto;

public class ChessGameDto {

    private final Long id;
    private final String turn;

    public ChessGameDto(final Long id, final String turn) {
        this.id = id;
        this.turn = turn;
    }

    public static ChessGameDto of(final Long id, final String turn) {
        return new ChessGameDto(id, turn);
    }

    public Long getId() {
        return id;
    }

    public String getTurn() {
        return turn;
    }
}
