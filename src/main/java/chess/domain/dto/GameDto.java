package chess.domain.dto;

public class GameDto {
    private final int id;
    private final boolean status;
    private final String turn;

    public GameDto(int id, boolean status, String turn) {
        this.id = id;
        this.status = status;
        this.turn = turn;
    }

    public int getId() {
        return id;
    }

}
