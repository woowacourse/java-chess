package chess.dto;

public class TurnDto {

    private String turn;

    public TurnDto(String turn) {
        this.turn = turn;
    }

    public String getTurn() {
        return turn;
    }

    public void setTurn(String turn) {
        this.turn = turn;
    }
}
