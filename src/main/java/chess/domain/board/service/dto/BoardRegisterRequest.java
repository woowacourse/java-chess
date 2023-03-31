package chess.domain.board.service.dto;

public class BoardRegisterRequest {

    private final String position;
    private final String turn;

    public BoardRegisterRequest(final String position, final String turn) {
        this.position = position;
        this.turn = turn;
    }

    public String position() {
        return position;
    }

    public String turn() {
        return turn;
    }
}
