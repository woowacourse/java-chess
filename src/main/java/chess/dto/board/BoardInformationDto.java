package chess.dto.board;

public class BoardInformationDto {
    private final int id;
    private final String turn;

    public BoardInformationDto(int id, String turn) {
        this.id = id;
        this.turn = turn;
    }

    public int getId() {
        return id;
    }

    public String getTurn() {
        return turn;
    }
}
