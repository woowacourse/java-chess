package chess.web.dto;

public class TurnDto {

    private final String turn;

    public TurnDto(String turn) {
        this.turn = turn;
    }

    public String getTurn() {
        return turn;
    }
}
