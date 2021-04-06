package chess.domain.DTO;

public class TurnDTO {
    private final String turn;

    private TurnDTO(String turn) {
        this.turn = turn;
    }

    public static TurnDTO of(String turn) {
        return new TurnDTO(turn);
    }

    public String getTurn() {
        return turn;
    }
}
